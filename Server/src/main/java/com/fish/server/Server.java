package com.fish.server;

import com.fish.core.NotesConstants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import javax.management.relation.RoleUnresolved;

public class Server {

    private ServerSocket socket;
    private boolean running = true;

    public Server() {
        while(running) {
            while (socket == null || socket.isClosed())
                setupNet();

            InputStreamReader reader = new InputStreamReader(System.in);



        }

    }

    private void setupNet() {
        try {
            socket = new ServerSocket(NotesConstants.PORT, 10, InetAddress.getByName(NotesConstants.IP));
        } catch(UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
    }


    public static void main(String[] args) {
        while(true) {
            try {
                System.out.println(System.in.available());
                while(System.in.available() > 0) {
                    System.out.print((char) System.in.read());
                }
                System.out.println();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //new Server();
    }


}
