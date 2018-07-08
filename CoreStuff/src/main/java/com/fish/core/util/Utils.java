package com.fish.core.util;


import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

    public static String getStackTrace(Throwable error) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);
        return sw.toString();
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static String getElementClasses(Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("types=[");
        for(int i = 0; i < args.length; i++) {
            sb.append(args[i].getClass().toString());
            if(i != args.length - 1) {
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
