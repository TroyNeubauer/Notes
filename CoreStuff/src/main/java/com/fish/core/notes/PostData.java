package com.fish.core.notes;

public abstract class PostData {
    //Returns an actor every time. Remember to cast
    public Object getActor() {
        return Core.creatorMap.get(this.getClass());
    }
}
