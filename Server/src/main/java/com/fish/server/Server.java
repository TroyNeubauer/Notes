package com.fish.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fish.core.NotesConstants;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.School;
import com.fish.core.util.Utils;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

import javax.management.relation.RoleUnresolved;

import sun.misc.Unsafe;

public class Server {

    private static final File DATABASE_FILE = new File("/home/ec2-user/Database.dat");

    public static final byte[] DEFAULT_PROFILE_PIC = new byte[0];
    private ServerSocket socket;
    private volatile boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    public Database database;

    public Server() {
        ServerBackend.init(this);
        if(DATABASE_FILE.exists()) {
            try {
                Input in = new Input(new FileInputStream(DATABASE_FILE));
                database = Utils.kryo.readObject(in, Database.class);
                in.close();
                System.out.println("Loading database from " + DATABASE_FILE);
            } catch (Exception e) {
                System.err.println("Unable to load database");
                throw new RuntimeException(e);
            }
        } else {
            database = new Database(64, 1000, 32, 128);
            System.out.println("Creating database at " + DATABASE_FILE);
        }
        setupNet();
        setupThread();
        while(running) {
            try {
                if (System.in.available() > 0) {
                    String line = scanner.nextLine();
                    if(Commands.parse(this, line)) {
                        stop();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void stop() {
        close();
        System.out.println("Server exiting!");
        System.exit(0);
    }

    private void setupThread() {
        Thread waitThread = new Thread(() -> {
            System.out.println("Waiting for nre connections...");
            while(running) {
                try {

                    Socket newSocket = socket.accept();
                    System.out.println("Accecpted new client connection from " + newSocket.getRemoteSocketAddress().toString());
                    Client client = new Client(newSocket);
                    new ServerBackend.ClientThread(client);
                } catch (IOException e) {

                }
            }
        });
        waitThread.start();
    }

    public void close() {
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerBackend.close();
        try {
            Output out = new Output(new FileOutputStream(DATABASE_FILE));
            Utils.kryo.writeObject(out, database);
            out.close();
        } catch (Exception e) {
            System.err.println("Unable to save database");
            throw new RuntimeException(e);
        }
    }

    private void setupNet() {
        try {
            socket = new ServerSocket(NotesConstants.PORT);
        } catch(UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public School getSchool(Client sender) {
        if(sender.getAccount().getAccount().getSchool() == -1) return null;
        return database.getSchoolByID(sender.getAccount().getAccount().getSchool());
    }


    public DatabaseAccount registerUser(String username, char[] password, String email) {
        return database.registerUser(username, password, email);
    }

    public boolean areCredentialsValid(String username, char[] password) {
        return database.areCredentialsValid(username, password);
    }


    public DatabaseAccount removeUser(String username) {
        return database.removeUser(username);
    }

    public boolean containsUser(String username) {
        return database.containsUsername(username);
    }

    public DatabaseAccount getAccount(String username) {
        return database.getAccount(username);
    }

    public static void main(String[] args) {
        new Server();
    }
}
