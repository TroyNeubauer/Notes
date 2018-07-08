package com.fish.server;

import com.fish.core.notes.BackendRequest;
import com.fish.core.notes.BackendResponse;
import com.fish.core.notes.Course;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PublicAccount;
import com.fish.core.notes.School;
import com.fish.core.util.ErrorString;
import com.fish.core.util.Utils;

import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.fish.server.Server.kryo;

public class ServerBackend {
    private static Server server;
    private static final HashMap<String, Method> methods = new HashMap<String, Method>();

    public static void init(Server server) {
        if(ServerBackend.server != null) throw new RuntimeException("Already inited!");
        ServerBackend.server = server;
        for(Method method : ServerBackend.class.getMethods()) {
            if(method.getAnnotations().length == 1) {
                if(method.getAnnotations()[0].annotationType() == Callable.class) {
                    System.out.println("Method " + method + " passes!");
                }
            } else {
                System.out.println("\t\tMethod " + method + " fails!");
            }
        }
    }

    public static class ClientThread implements Runnable {
        private Thread thread;
        private Client client;

        public ClientThread(Client client) {
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            Socket socket = client.socket;
            while(socket.isConnected()) {
                try {
                    BackendRequest request = kryo.readObject(client.in, BackendRequest.class);
                    Object result = invoke(request.getMethodName(), client, request.getArgs());
                    BackendResponse response = new BackendResponse(result, request.getId());
                    kryo.writeObject(client.out, response);
                } catch(ClassCastException e) {
                    System.err.println("Invalid class! Expected " + BackendRequest.class);
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    System.err.println("Random runtime exception");
                    e.printStackTrace();
                }
            }
            System.out.println("Ending client thread " + client);
        }
    }

    public static Object invoke(String name, Client sender, Object... args) {
        Method method = methods.get(name);
        if(method == null) {
            return new ErrorString("Unable to run method with args " + Utils.getElementClasses(args));
        }
        Object[] finalArgs = new Object[args.length + 1];
        finalArgs[0] = sender;
        System.arraycopy(args, 0, finalArgs, 1, args.length);//Copy all normal args
        try {
            return method.invoke(null, args);
        } catch(Exception e) {
            return new ErrorString("Unable to ");
        }
    }

    @Callable
    public static PublicAccount getAccount(Client sender, long id) {
        DatabaseAccount account = server.database.getAccountByID(id);
        if(account == null) return null;
        return account.toPublicAccount();
    }

    @Callable
    public static School getSchool(Client sender, long id) {
        return server.database.getSchoolByID(id);
    }

    @Callable
    public static Course getClass(Client sender, long id) {
        if(sender.getAccount() == null) return null;
        School school = server.getSchool(sender);
        return server.database.getClassByID(school, id);
    }

    @Callable
    public static boolean joinClass(Client sender, Course course) {
        if(sender.getAccount() == null) return false;
        School school = server.getSchool(sender);
        if(school == null) return false;
        return server.database.joinClass(school, sender.getAccount(), course);
    }

    @Callable
    public static LoginResult login(Client sender, String username, char[] password) {
        if(server.areCredentialsValid(username, password)) {
            DatabaseAccount account = server.getAccount(username);
            return new LoginResult("", account.getAccount());
        } else {
            return new LoginResult("Invalid password", null);
        }
    }

    @Callable
    public static LoginResult register(Client sender, String username, char[] password, String email) {
        if(server.containsUser(username)) {
            return new LoginResult("Username already in use!", null);
        } else if(server.database.containsEmail(email)){
            return new LoginResult("Email already in use!", null);
        } else {
            DatabaseAccount account = server.registerUser(username, password, email);
            sender.setAccount(account);
            return new LoginResult("", account.getAccount());

        }
    }

    @Callable
    public static Post post(Client sender, String title, Course course, PostData data) {
        if(sender.getAccount() == null) return null;
        return server.database.post(sender.getAccount(), title, course, data);
    }

    @Callable
    public static Set<School> getAllSchools(Client sender) {
        return server.database.schools.keySet();
    }

    @Callable
    public static boolean setSchool(Client sender, long schoolID) {
        if(sender.getAccount() == null) return false;
        sender.getAccount().getAccount().setSchool(schoolID);
        return true;
    }

    @Callable
    public static boolean removeClass(Client sender, long courseID) {
        if(sender.getAccount() == null) return false;
        return sender.getAccount().getAccount().getClasses().remove(courseID);
    }


    @Callable
    public static Object getRelevantPosts(Client sender) {
        if(sender.getAccount() == null) return new ErrorString("Please login before requesting data");
        return server.database.getRelevantPosts(sender.getAccount());
 
    }




    //+1 for upvote, -1 for downvote
    public static boolean addUpvote(Client sender, long postID, int vote) {
        if(sender.getAccount() == null) return false;
        long accountID = sender.getAccount().getAccount().getID();
        DatabasePost post = server.database.posts.get(postID);
        if(vote == +1) addIfNotPresent(post.getUpvotes(), accountID);
        else if(vote == -1) addIfNotPresent(post.getDownvotes(), accountID);
        else return false;
        return true;
    }

    private static void addIfNotPresent(List<Long> upvotes, long accountID) {
        if(!upvotes.contains(accountID)) upvotes.add(accountID);
    }

    public static int getUpVotes(Client sender, long postID) {
        DatabasePost post = server.database.posts.get(postID);
        return post.getUpvotes().size();
    }


    public static Object getAllClasses(Client sender) {
        if(sender.getAccount() == null) return new ErrorString("Please login before requesting data");
        School school = server.database.getSchoolByID(sender.getAccount().getAccount().getSchool());
        return server.database.getAllClasses(school);
    }

}
