package com.fish.core.util;


import com.esotericsoftware.kryo.Kryo;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Utils {



    public static final Kryo kryo = new Kryo();
    private static final Unsafe unsafe = retriveUnsafe();
    private static final String UNSAFE_CLASS = "sun.misc.Unsafe";

    private static Unsafe retriveUnsafe() {
        try {
            Class<?> unsafeClass = null;
            try {
                unsafeClass = Class.forName(UNSAFE_CLASS);
            } catch (ClassNotFoundException e) {
                return null;
            }
            Field[] fields = unsafeClass.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Unsafe cast = (Unsafe) field.get(null);
                    return cast;
                } catch (ClassCastException e) {
                    // Ignore, there might be other static fields
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    static {
        if(unsafe != null) {
            kryo.setInstantiatorStrategy(new InstantiatorStrategy() {
                @Override
                public <T> ObjectInstantiator<T> newInstantiatorOf(final Class<T> type) {
                    return new ObjectInstantiator<T>() {
                        @Override
                        public T newInstance() {
                            try {
                                return (T) unsafe.allocateInstance(type);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                }
            });
        }
    }


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
