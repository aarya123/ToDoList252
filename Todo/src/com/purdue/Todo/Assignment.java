package com.purdue.Todo;

/**
 * Created by Sean on 4/29/14.
 */
public class Assignment {
    private Course mCourse;
    private String mDueDate;
    private String mCategory;

    Assignment(){
        mCourse = new Course();
        mDueDate = null;
        mCategory = null;
    }
    Assignment(Course course, String date, String category){
        mCourse = course;
        mDueDate = date; //date in form year-month-day 00:00:00
        mCategory = category;
    }

    Course getCourse(){
        return mCourse;
    }

    String getDueDate(){
        return mDueDate;
    }

    String getCategory(){
        return mCategory;
    }

    void setCourse(Course course){
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
