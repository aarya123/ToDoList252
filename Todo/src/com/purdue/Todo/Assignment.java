package com.purdue.Todo;

/**
 * Created by Sean on 4/29/14.
 */
public class Assignment {
    private String mCourse;
    private String mDueDate;
    private String mCategory;

    Assignment(){
        mCourse = null;
        mDueDate = null;
        mCategory = null;
    }
    Assignment(String course, String date, String category){
        mCourse = course;
        mDueDate = date;
        mCategory = category;
    }

    String getCourse(){
        return mCourse;
    }

    String getDueDate(){
        return mDueDate;
    }

    String getCategory(){
        return mCategory;
    }

    void setCourse(String course){
        mCourse = course;
    }

    void setDueDate(String dueDate){
        mDueDate = dueDate;
    }

    void setCategory(String category){
        mCategory = category;
    }
    
    public String toString(){
    	String assignment_details = mCourse + "%" + mDueDate + "%" + mCategory;
    	return assignment_details;
    }

}
