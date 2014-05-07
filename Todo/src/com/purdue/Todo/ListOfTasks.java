package com.purdue.Todo;


import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfTasks extends ListActivity{
	TaskList_ArrayAdapter adapter;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    //get all the assignments from all the user's courses
	    /*int numOfCourses = User.getCurrentUser().getCourses().length;
	   
	    for(int i=0; i<numOfCourses; i++){
	    	
	    	int numOfAssignments = User.getCurrentUser().getCourses()[i].getAssignments().length;
	    	Log.d("PRANAV_TITLE", User.getCurrentUser().getCourses()[i]+" has "+Integer.toString(numOfAssignments));
	    	Assignment[] assignmentsInThisCourse = User.getCurrentUser().getCourses()[i].getAssignments();
	    	
	    	for(int j=0; j<numOfAssignments; j++){
	    		Log.d("PRANAV", assignmentsInThisCourse[j].toString());
	    		assignment_list.add(assignmentsInThisCourse[j]);
	    	}
	    }*/
	    
	    /*Assignment ass1 = new Assignment(User.getCurrentUser().getCourses()[0].getAssignments()[0]);
	    Assignment ass2 = new Assignment(User.getCurrentUser().getCourses()[0], "2014-05-18 12:31:22","Homework");
	    Assignment ass3 = new Assignment(User.getCurrentUser().getCourses()[0], "2014-06-10 05:43:11","Prelab");
	    Assignment ass4 = new Assignment(User.getCurrentUser().getCourses()[0], "2014-06-02 08:42:11","Test");
	    Assignment ass5 = new Assignment(User.getCurrentUser().getCourses()[0], "2014-07-14 21:12:08","Lab");
	    
	    assignment_list.add(ass1);
	    assignment_list.add(ass2);
	    assignment_list.add(ass3);
	    assignment_list.add(ass4);
	    assignment_list.add(ass5);*/
	    
	    ArrayList<Assignment> assignments = User.getCurrentUser().getAllAssignments();
	    for(Assignment a : assignments){
	    	Log.d("PRANAV", a.toString());
	    }
	    adapter = new TaskList_ArrayAdapter(this, assignments);
	    
	    /*ArrayAdapter<Assignment> adapter = new ArrayAdapter<Assignment>(this,
	    		R.layout.task_element_in_list, assignment_list);*/
	    setListAdapter(adapter);
	  }
    
    @Override
    // Inflate action bar menu items
    public boolean onCreateOptionsMenu(Menu menu) { 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_task_optionsmenu, menu); //displays the "add task" button
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    // Handle presses on the action bar items
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent intent = new Intent(getApplicationContext(), CourseChooserActivity.class);
                startActivity(intent);
                return true;
         
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void onListItemClick(ListView l, View v, int position, long id) {
        Assignment a = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), EditAssignmentActivity.class);
        
        //Toast.makeText(getApplicationContext(), a.getCourse().getName()+"%"+a.toString(), Toast.LENGTH_LONG).show();
        
        intent.putExtra("Assignment Object Details", a.getCourse().getName()+"%"+a.toString());
        intent.putExtra("Course_id", a.searchCourse().getId());
        
        startActivity(intent);
    }
    
}
