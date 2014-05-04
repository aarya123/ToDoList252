package com.purdue.Todo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nathan on 4/29/2014.
 */
public class User {
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
        SharedPreferences settings = SplashActivity.context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("currentUser", currentUser.id);
    }

    public static User currentUser = null;

    private int id;
    private Course[] courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public User(int id, Course[] courses) {
        this.id = id;
        this.courses = courses;
    }
}
