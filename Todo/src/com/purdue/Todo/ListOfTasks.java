package com.purdue.Todo;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListOfTasks extends ListActivity {
    TaskList_ArrayAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Assignment> assignment_list = new ArrayList<Assignment>();
        //String[] values = {"a","b","c","d","e","f","g","h"};
       /* Assignment ass1 = new Assignment("2014-05-21 00:12:31", "Lab", -1);
        Assignment ass2 = new Assignment("2014-05-18 12:31:22", "Homework", -1);
        Assignment ass3 = new Assignment("2014-06-10 05:43:11", "Prelab", -1);
        Assignment ass4 = new Assignment("2014-06-02 08:42:11", "Test", -1);
        Assignment ass5 = new Assignment("2014-07-14 21:12:08", "Lab", -1);

        assignment_list.add(ass1);
        assignment_list.add(ass2);
        assignment_list.add(ass3);
        assignment_list.add(ass4);
        assignment_list.add(ass5);*/

        adapter = new TaskList_ArrayAdapter(this, assignment_list);

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

        //Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_LONG).show();

        intent.putExtra("Assignment Object Details", a.toString());

        startActivity(intent);
    }

}
