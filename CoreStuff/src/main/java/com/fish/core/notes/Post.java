package com.fish.core.notes;

public class Post {
    private PostData data;
    private String title;
    private long posterID;

    public Post(PostData data, String title, long posterID) {
        this.data = data;
        this.title = title;
        this.posterID = posterID;
    }

    public PostData getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public long getPosterID() {
        return posterID;
    }
}
