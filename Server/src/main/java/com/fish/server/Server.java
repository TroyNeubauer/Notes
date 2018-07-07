package com.fish.server;

import com.fish.core.NotesConstants;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.util.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

import javax.management.relation.RoleUnresolved;

public class Server {

    public static final byte[] DEFAULT_PROFILE_PIC = new byte[0];
    private ServerSocket socket;
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private Database database;

    public Server() {
        while(running) {
            while (socket == null || socket.isClosed())
                setupNet();
            try {
                if (System.in.available() > 0) {
                    String line = scanner.next();
                    System.out.println("line: " + line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }

    }

    private void setupNet() {
        try {
            socket = new ServerSocket(NotesConstants.PORT, 10, InetAddress.getByName(NotesConstants.IP));
        } catch(UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerUser(String username, char[] password, String email) {
        database.registerUser(username, password, email);
    }

    public boolean areCredentialsValid(String username, char[] password) {
        return database.areCredentialsValid(username, password);
    }


    public DatabaseAccount removeUser(String username) {
        return null;
    }

    public boolean containsUser(String username) {
        return false;
    }

    public DatabaseAccount getAccount(String username) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Utils.concat(new byte[]{1,2,3,4,5}, new byte[]{7,8,9,10,12})));
        System.exit(0);
        new Server();
    }
}
