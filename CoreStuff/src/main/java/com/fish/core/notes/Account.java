package com.fish.core.notes;

import java.util.Set;

public class Account extends PublicAccount {

	public String email;
	private long schoolID;
	private Set<Long> classes;
	private int points, coins;

	public Account(long id, String username, byte[] profilePic, String email) {
	    super(id, username, profilePic);
		this.email = email;
		this.points = 0;
		this.coins = 0;
	}
	public void setPoints(int point) {points = point;}
	public int getPoints() {return points;}

	public void setCoins(int coin) {coins = coin;}
	public int getCoins() {return coins;}

	public String getEmail() {
		return email;
	}

    public Set<Long> getClasses() {
        return classes;
    }

    public long getSchool() {
        return schoolID;
    }

    @Override
	public int hashCode() {
	    long id = getID();
		return (int) ((id >> 32) ^ id);
	}

	@Override
	public String toString() {
		return "Account [id=" + getID() + ", username=" + getUsername() + ", email=" + email + "]";
	}
}
