package com.fish.core.notes;

public class Post extends DefaultNotesObject {
    private PostData data;
    private String title;
    private long posterUserID;

    public Post(long postID, PostData data, String title, long posterID) {
        super(postID);
        this.data = data;
        this.title = title;
        this.posterUserID = posterID;
    }

    public PostData getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public long getPosterUserID() {
        return posterUserID;
    }

    @Override
    public String toString() {
        return "Post{" +
                "data=" + data +
                ", title='" + title + '\'' +
                ", posterUserID=" + posterUserID +
                '}';
    }
}
