package com.purdue.Todo;

/**
 * Created by Nathan on 4/29/2014.
 */
public class Course {
    private int id;
    private String name;
    private int color;
    private Assignment[] assignments;

    public Course(int id, String name, int color, Assignment[] assignments) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.assignments = assignments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Assignment[] getAssignments() {
        return assignments;
    }

    public void setAssignments(Assignment[] assignments) {
        this.assignments = assignments;
    }
}
