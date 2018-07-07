package com.fish.core.notes;


public final class BackendResponse {
    public Object result;
    public long id;

    public BackendResponse(Object result, long id) {
        this.result = result;
        this.id = id;
    }
}
