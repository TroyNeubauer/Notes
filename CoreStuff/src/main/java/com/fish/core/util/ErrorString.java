package com.fish.core.util;


public class ErrorString {
    private String string;

    public ErrorString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "ErrorString{" +
                "string='" + string + '\'' +
                '}';
    }
}
