package com.fish.core.notes;

import java.util.HashMap;
import java.util.Map;

public class Core {
    public static Map<Class<?>, Creator> creatorMap = new HashMap<Class<?>, Creator>();

    public interface Creator {
        public Object create(Post post);
    }
}
