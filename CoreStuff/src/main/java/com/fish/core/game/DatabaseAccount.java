package com.fish.core.game;

import com.fish.core.game.Account;

import java.util.Arrays;

/**
 * Represents a user's account
 * 
 * @author Troy Neubauer
 *
 */
public class DatabaseAccount {

	private Account account;

	/** The number of iterations used to hash their password */
	private int iterations;
	/** The user's specific salt */
	private byte[] salt;
	/** The hash of the user's password */
	private byte[] hash;

	public DatabaseAccount(Account account, int iterations, byte[] salt, byte[] hash) {
		this.account = account;
		this.iterations = iterations;
		this.salt = salt;
		this.hash = hash;
	}

	public Account getAccount() {
		return account;
	}

	public int getIterations() {
		return iterations;
	}

	public byte[] getSalt() {
		return salt;
	}

	public byte[] getHash() {
		return hash;
	}

	@Override
	public String toString() {
		return "DatabaseAccount [account=" + account + ", iterations=" + iterations + ", salt=" + Arrays.toString(salt) + ", hash=" + Arrays.toString(hash)
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + Arrays.hashCode(hash);
		result = prime * result + iterations;
		result = prime * result + Arrays.hashCode(salt);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseAccount other = (DatabaseAccount) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (!Arrays.equals(hash, other.hash))
			return false;
		if (iterations != other.iterations)
			return false;
		if (!Arrays.equals(salt, other.salt))
			return false;
		return true;
	}

}
