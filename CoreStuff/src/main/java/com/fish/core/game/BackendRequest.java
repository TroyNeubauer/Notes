package com.fish.core.game;


public final class BackendRequest {
    private String methodName;
    private Object[] args;


    public BackendRequest(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }


    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }
}
