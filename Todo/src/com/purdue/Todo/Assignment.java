package com.purdue.Todo;

/**
 * Created by Sean on 4/29/14.
 */
public class Assignment {
    private String DueDate;
    private String Category;
    private int id;

    public Assignment(String dueDate, String category, int id) {
        DueDate = dueDate;
        Category = category;
        this.id = id;
    }

    public String getDueDate() {

        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        String assignment_details = DueDate + "%" + Category;
        return assignment_details;
    }

}
