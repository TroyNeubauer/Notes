package com.fish.core.notes;


public class PublicAccount extends DefaultNotesObject {

    private String username;
    private byte[] profilePic;

    public PublicAccount(long id, String username, byte[] profilePic) {
        super(id);
        this.username = username;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
}
