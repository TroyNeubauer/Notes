package com.fish.notes.backend;


public class RequestInfo {
    public final long id;
    public volatile Object result;
    public volatile boolean done;

    public RequestInfo(long id) {
        this.id = id;
        this.result = null;
        this.done = false;
    }
}
