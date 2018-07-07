package com.fish.core.notes;

public class PostDataText extends PostData {
    private String text;

    public PostDataText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
