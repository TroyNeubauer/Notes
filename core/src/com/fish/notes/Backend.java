package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.fish.core.notes.Account;
import com.fish.core.notes.Course;
import com.fish.core.notes.DatabaseAccount;
import com.fish.core.notes.DatabasePost;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PostDataText;
import com.fish.core.notes.PublicAccount;
import com.fish.core.notes.School;
import com.fish.core.util.Utils;
import com.fish.notes.backend.Database;


import java.io.ByteArrayInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Backend {

    private static Database database;


    public static boolean isConnected() {
        return true;
    }

    public static void start() {
        FileHandle dataBaseFile = Gdx.files.internal("database.dat");
        if(dataBaseFile.exists()) {
            database = Utils.readObject(Database.class, new ByteArrayInputStream(dataBaseFile.readBytes()));
        } else {
            database = new Database(64, 1000, 32, 32);
            School oakton = new School(0, "Oakton High School", 38.87738, -77.28282);
            database.schools.put(oakton, new HashMap<Course, List<Long>>());
            Course english = new Course(0, "English 10 Honors");
            Course csa = new Course(1, "Computer Science A");
            Course chem = new Course(2, "Chemistry Honors");

            database.joinClass(oakton, Notes.account, english);
            database.joinClass(oakton, Notes.account, csa);
            database.joinClass(oakton, Notes.account, chem);

            database.post(Notes.account, "Pronouns Notes", english, new PostDataText("Pronouns are words that substitute for nouns.\n" +
                    "Every pronoun must have a clear antecedent (the word for which the pronoun stands)."));
            database.post(Notes.account, "Noun Verb Agreement", english, new PostDataText("Nouns and verbs need to agree on number. They will be either singular or plural.\n" +
                    "The following sentences do not make sense because the nouns and their verbs do not agree in number:"));


        }
    }

    public static void init() {
        start();
    }

    public static PublicAccount getAccount(long id) {
        System.out.println(id);
        return database.getAccountByID(id).toPublicAccount();
    }

    public static School getSchool(long id) {
        return database.getSchoolByID(id);
    }

    public static Course getClass(long id) {
        return database.getClassByID(database.getSchoolByID(0), id);
    }

    public static boolean joinClass(Course course) {
        return database.joinClass(database.getSchoolByID(0), Notes.account, course);
    }

    public static Post post(String title, Course course, PostData data) {
        return database.post(Notes.account, title, course, data);
    }


    public static Set<School> getAllSchools() {
        return database.schools.keySet();
    }

    public static boolean setSchool(School school) {
        Notes.account.getAccount().setSchool(school.getID());
        return true;
    }

    public static boolean removeClass(Course course) {
        Notes.account.getAccount().getClasses().remove(course.getID());
       return true;
    }

    public static List<Post> getRelevantPosts() {
        List<Post> result = new ArrayList<Post>();
        for(DatabasePost post : database.posts.values()) {
            result.add(post.getPost());
        }
        return result;
    }

    //+1 for upvote, -1 for downvote
    public static boolean addUpvote(Post post, int vote) {
        long accountID = Notes.account.getAccount().getID();
        com.fish.core.notes.DatabasePost rawPost = database.posts.get(post.getID());
        if(vote == +1) addIfNotPresent(rawPost.getUpvotes(), accountID);
        else if(vote == -1) addIfNotPresent(rawPost.getDownvotes(), accountID);
        else return false;
        return true;
    }

    private static void addIfNotPresent(List<Long> upvotes, long accountID) {
        if(!upvotes.contains(accountID)) upvotes.add(accountID);
    }

    public static int getUpvotes(Post post) {
        com.fish.core.notes.DatabasePost rawPost = database.posts.get(post.getID());
        return rawPost.getUpvotes().size();
    }

    public static Set<Course> getAllClasses() {
        return database.getAllClasses(getSchool(0));
    }

    public static Post getPost(long id) {
        return database.posts.get(id).getPost();
    }

    public static List<Long> getBoughtPosts() {
        return Notes.account.getBoughtPosts();
    }
}
