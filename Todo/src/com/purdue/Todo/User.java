package com.purdue.Todo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

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
        editor.commit();
    }

    public static User currentUser = null;

    private int id;
    private Course[] Courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course[] getCourses() {
        if (Courses == null)
            Courses = new Course[0];
        return Courses;
    }

    public void setCourses(Course[] courses) {
        this.Courses = courses;
    }

    public User(int id, Course[] courses) {
        this.id = id;
        this.Courses = courses;
    }

    public ArrayList<Assignment> getAllAssignments() {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : Courses)
            assignments.addAll(Arrays.asList(course.getAssignments()));
        return assignments;
    }
}
