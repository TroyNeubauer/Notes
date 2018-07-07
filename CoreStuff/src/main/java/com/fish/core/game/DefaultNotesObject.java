package com.fish.core.game;

public class DefaultNotesObject implements NotesObject {
    private long id;

    public DefaultNotesObject(long id) {
        this.id = id;
    }

    @Override
    public long getID() {
        return id;
    }
}
