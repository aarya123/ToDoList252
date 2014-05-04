package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * Created by Sean on 4/22/14.
 */
public class CourseChooserActivity extends Activity {

    String myClasses[] = {"CHM 115", "MA 416", "CS 307", "CS 252"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_chooser);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrayAdapter<Course> mAdapter = new ArrayAdapter<Course>(this, R.layout.grid_element, User.currentUser.getCourses());
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(getApplicationContext(), DueDateChooserActivity.class);
                intent.putExtra("coursePos", pos);
                startActivity(intent);
            }
        });

    }
    
    @Override
    // Inflate action bar menu items
    public boolean onCreateOptionsMenu(Menu menu) { 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_tasks_optionsmenu, menu); //displays the "list" button
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    // Handle presses on the action bar items
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.id.action_task_list:
                Intent intent = new Intent(getApplicationContext(), ListOfTasks.class);
                startActivity(intent);
                return true;
         
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}