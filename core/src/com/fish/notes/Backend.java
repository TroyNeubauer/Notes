package com.fish.notes;

import com.fish.core.game.Account;
import com.fish.core.game.Course;
import com.fish.core.game.Post;
import com.fish.core.game.LoginResult;
import com.fish.core.game.PostData;
import com.fish.core.game.School;

public class Backend {
    public static School getSchool(long id) {
        return null;
    }

    public static Course Class(long id) {
        return null;
    }

    public static void joinClass(Account account, Course course) {
        account.getClasses().add(course.getID());
    }



    public static LoginResult login(String username, String password) {
        return null;
    }

    public static LoginResult register(String username, String password, String email) {
        return null;
    }

    public static Post post(String title, Course course, PostData data) {
        return null;
    }
}
