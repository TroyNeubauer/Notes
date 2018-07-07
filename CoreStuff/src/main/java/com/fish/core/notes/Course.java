package com.fish.core.notes;

public class Course extends DefaultNotesObject {
    private String name;

    public Course(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
