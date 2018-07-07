package com.fish.core.game;


public class LoginResult {
    private String message;
    private Account account;

    public LoginResult(String message, Account account) {
        this.message = message;
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public Account getAccount() {
        return account;
    }

    /**
     * Returns true if the login was succscful. Otherwise false
     * If this method returns true, account is guarnteed to be non-null.
     */
    public boolean isSuccess() {
        return account != null;
    }
}
