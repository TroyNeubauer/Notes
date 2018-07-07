package com.fish.core.notes;

public class Post {
    private PostData data;
    private String name;
    private long posterID;

    public Post(PostData data, String name, long posterID) {
        this.data = data;
        this.name = name;
        this.posterID = posterID;
    }

    public PostData getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public long getPosterID() {
        return posterID;
    }
}
