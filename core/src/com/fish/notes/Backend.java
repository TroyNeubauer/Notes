package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.fish.core.NotesConstants;
import com.fish.core.game.Account;
import com.fish.core.game.Course;
import com.fish.core.game.Post;
import com.fish.core.game.LoginResult;
import com.fish.core.game.PostData;
import com.fish.core.game.School;

import java.util.concurrent.atomic.AtomicBoolean;

public class Backend implements Runnable {

    private Backend() {

    }

    @Override
    public void run() {
        SocketHints hints = new SocketHints();
        while(socket == null) {
            try {
                socket = Gdx.net.newClientSocket(Net.Protocol.TCP, NotesConstants.IP, NotesConstants.PORT, hints);
            } catch(Exception e) {
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }

        }
    }

    private Socket socket;
    private static Thread socketThread;
    private static AtomicBoolean running = new AtomicBoolean(false);

    private static void start() {
        if(socketThread != null) {
            throw new RuntimeException("thing already running!");
        }
        socketThread = new Thread(new Backend());
        running.set(true);
        socketThread.start();
    }

    private static Object getData(String methodName, Object... args) {
        if(!running.get()) {
            start();
        }

        return null;
    }

    public static School getSchool(long id) {
        return (School) getData("getSchool", id);
    }

    public static Course getClass(long id) {
        return (Course) getData("getClass", id);
    }

    public static void joinClass(Account account, Course course) {
        getData("joinClass", account, course);
    }



    public static LoginResult login(String username, String password) {
        return (LoginResult) getData("login", username, password);
    }

    public static LoginResult register(String username, String password, String email) {
        return (LoginResult) getData("register", username, password, email);
    }

    public static Post post(String title, Course course, PostData data) {
        return (Post) getData("post", title, course, data);
    }
}
