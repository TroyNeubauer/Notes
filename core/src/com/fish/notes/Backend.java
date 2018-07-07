package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.fish.core.NotesConstants;
import com.fish.core.game.Account;
import com.fish.core.game.Course;
import com.fish.core.game.Post;
import com.fish.core.game.LoginResult;
import com.fish.core.game.PostData;
import com.fish.core.game.PublicAccount;
import com.fish.core.game.School;

import java.util.concurrent.atomic.AtomicBoolean;

public class Backend implements Runnable {

    public static boolean showConnectionDialog = true;

    private Backend() {

    }

    @Override
    public void run() {
        SocketHints hints = new SocketHints();
        hints.tcpNoDelay = false;
        hints.trafficClass = 0x02 | 0x04;//Cheap and reliable
        while(socket == null) {
            try {
                socket = Gdx.net.newClientSocket(Net.Protocol.TCP, NotesConstants.IP, NotesConstants.PORT, hints);
            } catch(GdxRuntimeException e) {
                if(showConnectionDialog) Notes.showDialog("Unable to connect to server!", "Check your connection");
                showConnectionDialog = false;
            } try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                break;
            }
        }
        if(!showConnectionDialog) {//if we connected after a while...
            Notes.showDialog("Connected to server!", "");
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

    public static PublicAccount getAccount(long id) {
        return (PublicAccount) getData("getAccount", id);
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
