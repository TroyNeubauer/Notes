package com.fish.core.game;

public class Account {

	public int id;
	public String username, email;

	public Account() {
	}

	public Account(int id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
}
