package com.fish.server;

import com.fish.core.notes.Account;
import com.fish.core.notes.Course;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PublicAccount;
import com.fish.core.notes.School;

public class ServerBackend {
    private static Server server;

    public static PublicAccount getAccount(Client sender, long id) {
        DatabaseAccount account = server.database.getAccountByID(id);
        if(account == null) return null;
        return account.toPublicAccount();
    }

    public static School getSchool(Client sender, long id) {
        return server.database.getSchoolByID(id);
    }

    public static Course getClass(Client sender, long id) {
        if(sender.getAccount() == null) return null;
        School school = server.getSchool(sender);
        return server.database.getClassByID(school, id);
    }

    public static boolean joinClass(Client sender, Course course) {
        if(sender.getAccount() == null) return false;
        School school = server.getSchool(sender);
        if(school == null) return false;
        return server.database.joinClass(school, sender.getAccount(), course);
    }

    public static LoginResult login(Client sender, String username, char[] password) {
        if(server.areCredentialsValid(username, password)) {
            DatabaseAccount account = server.getAccount(username);
            return new LoginResult("", account.getAccount());
        } else {
            return new LoginResult("Invalid password", null);
        }
    }

    public static LoginResult register(Client sender, String username, char[] password, String email) {
        if(server.containsUser(username)) {
            return new LoginResult("Username already in use!", null);
        } else if(server.database.containsEmail(email)){
            return new LoginResult("Email already in use!", null);
        } else {
            DatabaseAccount account = server.registerUser(username, password, email);
            sender.setAccount(account);
            return new LoginResult("", account.getAccount());

        }
    }

    public static Post post(Client sender, String title, Course course, PostData data) {
        if(sender.getAccount() == null) return null;
        server.database.post(sender.getAccount(), course, data);
    }
}
