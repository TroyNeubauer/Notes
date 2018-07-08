package com.fish.core.notes;

public class School extends DefaultNotesObject {
    private String name;
    private double lat, lng;

    public School(long id, String name, double lat, double lng) {
        super(id);
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
