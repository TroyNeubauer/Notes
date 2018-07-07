package com.fish.core.game;

import java.util.List;

public class Account extends DefaultNotesObject {

	public String username, email;
	private long schoolID;
	private List<Long> classes;
	private int points, coins;

	public Account(long id, String username, String email) {
	    super(id);
		this.username = username;
		this.email = email;
		this.points = 0;
		this.coins = 0;
	}
	public void setPoints(int point) {points = point;}
	public int getPoints() {return points;}

	public void setCoins(int coin) {coins = coin;}
	public int getCoins() {return coins;}

	public String getUsername() {
		return username;
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
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Account [id=" + getID() + ", username=" + username + ", email=" + email + "]";
	}
}
