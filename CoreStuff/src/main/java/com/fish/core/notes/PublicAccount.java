package com.fish.core.notes;

import com.sun.prism.Texture;

public class PublicAccount extends DefaultNotesObject {

    private String username;
    private Texture profilePic;

    public PublicAccount(long id, String username, Texture profilePic) {
        super(id);
        this.username = username;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public Texture getProfilePic() {
        return profilePic;
    }
}
