package com.fish.server;


import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fish.core.notes.Account;
import com.fish.core.notes.DatabaseAccount;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public Input in;
    public Output out;
    private DatabaseAccount account = null;
    public ClientState state = ClientState.DISCONNECTED;
    public Socket socket;

    public Client(Socket socket) {
        if(!socket.isConnected()) throw new IllegalArgumentException("Socket is not connected!");
        try {
            this.socket = socket;

            this.in = new Input(socket.getInputStream());
            this.out = new Output(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
            System.err.println("Error disconnecting client!:");
            e.printStackTrace();
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
