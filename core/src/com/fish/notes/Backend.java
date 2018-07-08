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
import com.fish.core.util.ErrorString;
import com.fish.core.util.Utils;
import com.fish.notes.backend.RequestInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Backend implements Runnable {

    public static boolean showConnectionDialog = true;

    public static final Object DISCONNECTED_FROM_SERVER = new Object();

    private static final AtomicLong IDs = new AtomicLong(0);
    private static final List<RequestInfo> requests = new ArrayList<RequestInfo>();

    private Backend() {

    }

    @Override
    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));
        System.out.println("Starting backend!");
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
                    e.printStackTrace();
                    showConnectionDialog = false;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("connected to server!");
            if(!showConnectionDialog) {//if we connected after a while...
                Notes.showDialog("Connected to server!", "");
            }
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
            while(running.get() && socket.isConnected()) {
                try {
                    BackendResponse response = Utils.readObject(BackendResponse.class, in);
                    System.out.println("got responce! " + response);
                    for(int i = 0; i < requests.size(); i++) {
                        RequestInfo request = requests.get(i);
                        if(request.id == response.id) {
                            request.result = response.result;
                            if(response.result instanceof ErrorString) {
                                Notes.showDialog("Invalid server request:", ((ErrorString) response.result).getString());
                            }
                            request.done = true;
                            System.out.println("Recieved! Setting id \"" + request.id + "\" to " + request.result);
                        }
                    }

                } catch(ClassCastException e) {
                    System.err.print("Different class recieved from server!");
                    e.printStackTrace();
                    continue;
                } catch(GdxRuntimeException e) {
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

    private static InputStream in;
    private static OutputStream out;
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

    public static void init() {
        start();
    }

    private static<T> T getData(String methodName, Class<T> type, Object... args) {
        if(!running.get())
            start();
        if(!isConnected())
            return null;
        long id = IDs.incrementAndGet();
        BackendRequest request = new BackendRequest(methodName, args, id);
        try {
            byte[] bytes = Utils.writeObject(request);
            System.out.println("Client writing " + bytes.length + " bytes");
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Send request " + request);
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

    private static void close() {
        if(running.get()) {
            running.set(false);
            try {
                in.close();
            } catch (IOException e) {
            }
            try {
                out.close();
            } catch (IOException e) {
            }

            socket.dispose();
            try {
                socketThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
