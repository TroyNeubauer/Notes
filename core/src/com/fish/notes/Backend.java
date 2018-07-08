package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fish.core.NotesConstants;
import com.fish.core.notes.Account;
import com.fish.core.notes.BackendRequest;
import com.fish.core.notes.BackendResponse;
import com.fish.core.notes.Course;
import com.fish.core.notes.Post;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PublicAccount;
import com.fish.core.notes.School;
import com.fish.notes.backend.RequestInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Backend implements Runnable {

    public static boolean showConnectionDialog = true;
    private static final Kryo kryo = new Kryo();

    public static final Object DISCONNECTED_FROM_SERVER = new Object();

    private static final AtomicLong IDs = new AtomicLong(0);
    private static final List<RequestInfo> requests = new ArrayList<RequestInfo>();

    private Backend() {

    }

    @Override
    public void run() {
        SocketHints hints = new SocketHints();
        hints.tcpNoDelay = false;
        hints.trafficClass = 0x02 | 0x04;//Cheap and reliable
        while(running.get()) {//In case we randomly disconnect in the middle of things
            while (socket == null || !socket.isConnected()) {
                try {
                    socket = Gdx.net.newClientSocket(Net.Protocol.TCP, NotesConstants.IP, NotesConstants.PORT, hints);
                } catch (GdxRuntimeException e) {
                    if (showConnectionDialog)
                        Notes.showDialog("Unable to connect to server!", "Check your connection");
                    showConnectionDialog = false;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            if(!showConnectionDialog) {//if we connected after a while...
                Notes.showDialog("Connected to server!", "");
            }
            try {
                in = new Input(socket.getInputStream());
                out = new Output(socket.getOutputStream());
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
            while(running.get() && socket.isConnected()) {
                try {
                    BackendResponse response = kryo.readObject(in, BackendResponse.class);
                    for(int i = 0; i < requests.size(); i++) {
                        RequestInfo request = requests.get(i);
                        if(request.id == response.id) {
                            request.result = response.result;
                            request.done = true;
                            System.out.println("Recieved! Setting id \"" + request.id + "\" to " + request.result);
                        }
                    }

                } catch(ClassCastException e) {
                    System.err.print("Different class recieved from server!");
                    e.printStackTrace();
                    continue;
                } catch(GdxRuntimeException e) {
                    System.err.println("In 12345 catch in Backend!");
                    e.printStackTrace();
                    continue;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Input in;
    private static Output out;
    private static Socket socket;
    private static Thread socketThread;
    private static AtomicBoolean running = new AtomicBoolean(false);

    public static boolean isConnected() {
        if(socket == null) return false;
        return socket.isConnected();
    }

    public static void stop() {
        socket.dispose();
        try {
            socketThread.join();
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void start() {
        if(socketThread != null) {
            return;
        }
        socketThread = new Thread(new Backend());
        running.set(true);
        socketThread.start();
    }

    private static<T> T getData(String methodName, Class<T> type, Object... args) {
        if(!running.get())
            start();
        if(!isConnected())
            return null;
        long id = IDs.incrementAndGet();
        BackendRequest request = new BackendRequest(methodName, args, id);
        kryo.writeObject(out, request);
        RequestInfo info = new RequestInfo(id);
        requests.add(info);
        while(!info.done) {
            try {
                Thread.sleep(1);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
        Object result = info.result;
        System.out.println("got from server expected " + type + " got " + result.getClass() + "!");
        return (T) info.result;
    }

    public static PublicAccount getAccount(long id) {
        return getData("getAccount", PublicAccount.class, id);
    }

    public static School getSchool(long id) {
        return getData("getSchool", School.class, id);
    }

    public static Course getClass(long id) {
        return getData("getClass", Course.class, id);
    }

    public static boolean joinClass(Account account, Course course) {
        Boolean success = getData("joinClass", Boolean.class, course);
        if(success == null) return false;
        if(success) account.getClasses().add(course.getID());
        return success;
    }

    public static LoginResult login(String username, String password) {
        return getData("login", LoginResult.class, username, password.toCharArray());
    }

    public static LoginResult register(String username, String password, String email) {
        return getData("register", LoginResult.class, username, password.toCharArray(), email);
    }

    public static Post post(String title, Course course, PostData data) {
        return getData("post", Post.class, title, course, data);
    }


    public static Set<School> getAllSchools() {
        return getData("getAllSchools", Set.class);
    }

    public static boolean setSchool(School school) {
        Boolean result = getData("setSchool", Boolean.class, school.getID());
        if(result == null) return false;
        return result;
    }

    public static boolean removeClass(Course course) {
        Boolean result = getData("removeClass", Boolean.class, course.getID());
        if(result == null) return false;
        return result;
    }

    public static List<Post> getRelevantPosts() {
        return getData("getRelevantPosts", List.class);
    }

    //+1 for upvote, -1 for downvote
    public static boolean addUpvote(Post post, int vote) {
        Boolean result = getData("addUpvote", Boolean.class, post.getPosterUserID(), vote);
        if(result == null) return false;
        return result;
    }

    public static int getUpvotes(Post post) {
        Integer result = getData("getUpvotes", Integer.class, post.getPosterUserID());
        if(result == null) return -1;
        return result;
    }

    public static Set<Course> getAllClasses() {
        return (Set<Course>) getData("getAllClasses", Set.class);
    }

    public static Post getPost(long id) {
        return getData("getPost", Post.class, id);
    }

    public static List<Post> getBoughtPosts() {
        return (List<Post>) getData("getBoughtPosts", List.class);
    }

}
