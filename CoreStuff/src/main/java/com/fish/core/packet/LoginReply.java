package com.fish.core.packet;

import com.fish.core.notes.Account;

public class LoginReply {
	private Account account;

	public LoginReply(Account account) {
		this.account = account;
	}

    public Account getAccount() {
        return account;
    }
}
