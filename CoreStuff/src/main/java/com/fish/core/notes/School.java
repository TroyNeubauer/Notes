package com.fish.core.notes;

public class School extends DefaultNotesObject {
    private String name;
    private double lat, lng;
    private SchoolData data;

    public School(long id, String name, double lat, double lng, SchoolData data) {
        super(id);
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.data = data;
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

    public SchoolData getData() {
        return data;
    }
}
