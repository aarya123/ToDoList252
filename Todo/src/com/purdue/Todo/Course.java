package com.purdue.Todo;

/**
 * Created by Nathan on 4/29/2014.
 */
public class Course {
    private int id;
    private String Name;
    private String Color;
    private Assignment[] Assignments;

    public Course() {
        this.id = 0;
        this.Name = null;
        this.Color = "";
        this.Assignments = null;
    }

    public Course(int id, String name, String Color, Assignment[] assignments) {
        this.id = id;
        this.Name = name;
        this.Color = Color;
        this.Assignments = assignments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public Assignment[] getAssignments() {
        return Assignments;
    }

    public void setAssignments(Assignment[] assignments) {
        this.Assignments = assignments;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
