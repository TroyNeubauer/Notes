package com.fish.core.packet;

import com.fish.core.game.Account;

public class LoginReply {
	private Account account;

	public LoginReply(Account account) {
		this.account = account;
	}

    public Account getAccount() {
        return account;
    }
}
