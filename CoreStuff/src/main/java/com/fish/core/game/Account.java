package com.fish.core.game;

import java.util.List;

public class Account extends PublicAccount {

	public String email;
	private long schoolID;
	private List<Long> classes;

	public Account(long id, String username, byte[] profilePic, String email) {
	    super(id, username, profilePic);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

    public List<Long> getClasses() {
        return classes;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Account [id=" + getID() + ", username=" + getUsername() + ", email=" + email + "]";
	}
}
