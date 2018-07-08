package com.fish.server;


import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fish.core.notes.Account;
import com.fish.core.notes.DatabaseAccount;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public Socket socket;
    public InputStream in;
    public OutputStream out;

    public ClientState state = ClientState.DISCONNECTED;
    private DatabaseAccount account = null;


    public Client(Socket socket) {
        if(!socket.isConnected()) throw new IllegalArgumentException("Socket is not connected!");
        try {
            this.socket = socket;
            this.in = socket.getInputStream();
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setAccount(DatabaseAccount account) {
        this.account = account;
        this.state = ClientState.CONNECTED_LOGGED_IN;
    }

    public DatabaseAccount getAccount() {
        return account;
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
        }
        account = null;
        state = ClientState.DISCONNECTED;
    }

    @Override
    public String toString() {
        return "Client{" +
                "account=" + account +
                ", state=" + state +
                ", socket=" + socket +
                '}';
    }
}
