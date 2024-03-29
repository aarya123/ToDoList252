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
import android.widget.Toast;

/**
 * Created by Sean on 4/22/14.
 */
public class CourseChooserActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_chooser);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrayAdapter<Course> mAdapter = null;
        if (User.currentUser != null)
            mAdapter = new ArrayAdapter<Course>(this, R.layout.grid_element, User.currentUser.getCourses());
        else
            Toast.makeText(this, "null :(", Toast.LENGTH_SHORT).show();
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(getApplicationContext(), DueDateChooserActivity.class);
                intent.putExtra("coursePos", pos);
                startActivity(intent);
            }
        });
/*        for (int i = 0; i < User.getCurrentUser().getAllAssignments().size(); i++)
            Log.d(("Assignment #" + i), User.getCurrentUser().getAllAssignments().get(i).getDueDate());*/
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