package com.fish.core.notes;

public class DefaultNotesObject implements NotesObject {
    private long id;

    public DefaultNotesObject(long id) {
        this.id = id;
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        return this.id == ((DefaultNotesObject) obj).getID();
    }
}
