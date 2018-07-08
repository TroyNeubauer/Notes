package com.fish.server;

import com.fish.core.notes.Account;
import com.fish.core.notes.Course;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostData;
import com.fish.core.notes.School;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Database {
    public int hashBytes, iterations, saltBytes;
    public byte[] pepper;
    public HashMap<String, DatabaseAccount> users = new HashMap<String, DatabaseAccount>();
    public HashMap<School, Map<Course, List<Long>>> schools = new HashMap<School, Map<Course, List<Long>>>();//map between schools and courses to the list of post id's
    public HashMap<DatabaseAccount, List<Long>> userPosts = new HashMap<DatabaseAccount, List<Long>>();//Maps users to a list of their posts
    public HashMap<Long, DatabasePost> posts = new HashMap<Long, DatabasePost>();
    private long totalUsers;

    private AtomicLong postCount = new AtomicLong(0L);

    private transient SecureRandom random = new SecureRandom();

    public Database(int hashBytes, int iterations, int saltBytesr, int pepperBytes) {
        this.hashBytes = hashBytes;
        this.iterations = iterations;
        this.saltBytes = saltBytes;

        this.pepper = new byte[pepperBytes];
        this.random.nextBytes(pepper);
        this.totalUsers = 0L;
    }

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

    public boolean containsUsername(String username) {
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
            return true;//They match!
        }
        return false;
    }

    public DatabaseAccount getAccount(String username) {
        return users.get(username);
    }

    public DatabaseAccount removeUser(String username) {
        DatabaseAccount account = users.get(username);
        if(account != null) users.remove(username);
        return account;
    }

    public DatabaseAccount getAccountByID(long id) {
        for(DatabaseAccount account : users.values()) {
            if(account.getAccount().getID() == id) return account;
        }
        return null;
    }

    public School getSchoolByID(long id) {
        for(School school : schools.keySet()) {
            if(school.getID() == id) return school;
        }
        return null;
    }

    public Course getClassByID(School school, long id) {
        Map<Course, List<Long>> classes = schools.get(school);
        if(classes == null) return null;
        for(Course course : classes.keySet()) {
            if(course.getID() == id) return course;
        }
        return null;
    }

    public boolean joinClass(School school, DatabaseAccount account, Course course) {
        if(account.getAccount().getSchool() == -1) return false;
        Account rawAccount = account.getAccount();
        if(school.equals(getSchoolByID(rawAccount.getSchool()))) {
            rawAccount.getClasses().add(course.getID());//Make sure the school the class is taught at it the same as the requester's school
            return true;
        } else
            return false;
    }

    public boolean containsEmail(String email) {
        for(DatabaseAccount account : users.values()) {
            if(account.getAccount().getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Post post(DatabaseAccount account, String title, Course course, PostData data) {
        School school = getSchoolByID(account.getAccount().getSchool());
        if(school == null) return null;
        long newID = postCount.incrementAndGet();
        Post post = new Post(newID, data, title, account.getAccount().getID());
        Map<Course, List<Long>> map = schools.get(school);
        List<Long> postIDs = map.get(course);
        if(postIDs == null) {
            postIDs = new ArrayList<Long>();
            map.put(course, postIDs);
        }
        postIDs.add(newID);

        List<Long> accountPosts = userPosts.get(account);
        if(accountPosts == null) {
            accountPosts = new ArrayList<Long>(1);
            userPosts.put(account, accountPosts);
        }
        DatabasePost realPost = new DatabasePost(post);
        accountPosts.add(newID);
        posts.put(newID, realPost);
        return post;
    }

    public List<Post> getRelevantPosts(DatabaseAccount account) {
        //TODO
        return null;
    }

    public Set<Course> getAllClasses(School school) {
        Map<Course, List<Long>> map = schools.get(school);
        if(map == null) return null;
        return map.keySet();
    }
}
