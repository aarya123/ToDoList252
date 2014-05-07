package com.purdue.Todo;

/**
 * Created by Sean on 4/29/14.
 */
public class Assignment {
	private String DueDate;
	private String Categories;
	private String Notes;
	private int id; 
	private Course course;

    public Assignment(String dueDate, String category, int id, Course course) {
        DueDate = dueDate;
        Categories = category;
        this.id = id;
        course = course;
    }

	public String getDueDate() {

		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getCategories() {
		return Categories;
	}

	public void setCategories(String categories) {
		Categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNotes(){
		return Notes;
	}

	public void setNotes(String Notes){
		this.Notes = Notes;
	}
	public String toString() {
		String assignment_details = DueDate + "%" + Categories + "%" + Integer.toString(id)
				+ "%" + Notes;
		return assignment_details;
	}

	public Course getCourse() {
		if (course == null) {
			for (Course c : User.getCurrentUser().getCourses()) {
				for (Assignment a : c.getAssignments()) {
					if (this.equals(a)) {
						course = c;
					}
				}
			}
		}
		return course;
	}
	
	static public Assignment getAssignmentByID(int id){
		for (Course c : User.getCurrentUser().getCourses()) {
			for (Assignment a : c.getAssignments()) {
				if (id==a.getId()) {
					return a;
				}
			}
		}
		//no assignment found
		return null;
	}
    public void setCourse(Course course) { this.course = course; }

}
