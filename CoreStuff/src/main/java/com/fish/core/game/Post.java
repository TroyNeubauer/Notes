package com.fish.core.game;

public class Post {
    private PostData data;
    private String name;

    public Post(PostData data, String name) {
        this.data = data;
        this.name = name;
    }

    public PostData getData() {
        return data;
    }

    public String getName() {
        return name;
    }


}
