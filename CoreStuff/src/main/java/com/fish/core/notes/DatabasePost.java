package com.fish.core.notes;


import com.fish.core.notes.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabasePost {
    private Post post;
    //List of account IDs that voted
    private List<Long> upvotes = new ArrayList<Long>(), downvotes = new ArrayList<Long>();

    public DatabasePost(Post post) {
        this.post = post;
    }


    public Post getPost() {
        return post;
    }

    public List<Long> getUpvotes() {
        return upvotes;
    }

    public List<Long> getDownvotes() {
        return downvotes;
    }
}
