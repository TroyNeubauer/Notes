package com.fish.server;

import com.fish.core.notes.Account;
import com.fish.core.notes.Course;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.Post;
import com.fish.core.notes.School;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public int hashBytes, iterations, saltBytes;
    public byte[] pepper;
    public HashMap<String, DatabaseAccount> users = new HashMap<String, DatabaseAccount>();
    public HashMap<School, List<Map<Course, Post>>> schools = new HashMap<School, List<Map<Course, Post>>>();
    private long totalUsers = 0L;

    private transient SecureRandom random = new SecureRandom();

    public DatabaseAccount registerUser(String username, char[] password, String email) {
        if (containsUsername(username))
            throw new IllegalStateException("An account with username \"" + username + "\" already exists!");
        byte[] salt = new byte[saltBytes];
        random.nextBytes(salt);

        byte[] hash = Security.getHashedPassword(password, salt, pepper, iterations, hashBytes);
        Account basicAccount = new Account(totalUsers++, username, Server.DEFAULT_PROFILE_PIC, email);
        DatabaseAccount account = new DatabaseAccount(basicAccount, iterations, salt, hash);
        users.put(username, account);
        return account;
    }

    private boolean containsUsername(String username) {
        return users.containsKey(username);
    }

    public boolean areCredentialsValid(String username, char[] password) {
        if (username == null)
            return false;
        if (password == null)
            return false;
        if (username.length() == 0 || password.length == 0)
            return false;

        if (containsUsername(username)) {
            DatabaseAccount account = users.get(username);
            byte[] storedHash = account.getHash();
            byte[] computedHash = Security.getHashedPassword(password, account.getSalt(), pepper, account.getIterations(), hashBytes);
            if (storedHash.length != computedHash.length)
                return false;
            for (int i = 0; i < storedHash.length; i++) {
                if (storedHash[i] != computedHash[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
