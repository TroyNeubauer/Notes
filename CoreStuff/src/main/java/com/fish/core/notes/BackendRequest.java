package com.fish.core.notes;


public final class BackendRequest {
    private String methodName;
    private Object[] args;
    private long id;

    public BackendRequest(String methodName, Object[] args, long id) {
        this.methodName = methodName;
        this.args = args;
        this.id = id;
    }


    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public long getId() {
        return id;
    }
}
