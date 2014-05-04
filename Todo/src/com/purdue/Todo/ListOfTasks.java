package com.purdue.Todo;


import java.util.ArrayList;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfTasks extends ListActivity{
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ArrayList<Assignment> assignment_list = new ArrayList<Assignment>();
	    //String[] values = {"a","b","c","d","e","f","g","h"};
	    Assignment ass1 = new Assignment("Chem 115", "2014-05-21 00:12:31","Lab");
	    Assignment ass2 = new Assignment("Phys 172", "2014-05-18 12:31:22","Homework");
	    Assignment ass3 = new Assignment("CS 240", "2014-06-10 05:43:11","Prelab");
	    Assignment ass4 = new Assignment("MA 352", "2014-06-02 08:42:11","Test");
	    Assignment ass5 = new Assignment("STAT 350", "2014-07-14 21:12:08","Lab");
	    
	    assignment_list.add(ass1);
	    assignment_list.add(ass2);
	    assignment_list.add(ass3);
	    assignment_list.add(ass4);
	    assignment_list.add(ass5);
	    
	    TaskList_ArrayAdapter adapter = new TaskList_ArrayAdapter(this, assignment_list);
	    
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
    
    /*public void onListItemClick(ListView l, View v, int position, long id) {
        Assignment e = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
        intent.putExtra(getString(R.string.START_FRAGMENT), "EventPageFragment");
        intent.putExtra(getString(R.string.EVENT), e);
        startActivity(intent);
    }*/
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      String item = (String) getListAdapter().getItem(position);
      Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
    }
    


      
}
