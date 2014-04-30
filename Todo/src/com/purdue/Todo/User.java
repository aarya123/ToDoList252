package com.purdue.Todo;

/**
 * Created by Nathan on 4/29/2014.
 */
public class User {
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
