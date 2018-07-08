package com.fish.server;


import com.fish.core.notes.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabasePost {
    private Post post;
    private List<Long> upvotes = new ArrayList<Long>(), downvotes = new ArrayList<Long>();

}
