package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by Sean on 4/22/14.
 */
public class DueDateChooserActivity extends Activity {
    //*******************
    // used as a workaround for onDateChangeListener
    // The listener is called when the user scrolls to a different month
    // without actually changing the date so this checks whether the date
    // was actually changed.
    Long startDate;
    //*******************
    int coursePos;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duedate_chooser);

        TextView courseSelected = (TextView) findViewById(R.id.selectedCourse);

        if(getIntent().hasExtra("coursePos")){
            coursePos = getIntent().getIntExtra("coursePos", -1);
            if(coursePos < 0){ Log.d("Sean", "Invalid Course Pos"); }
        }
        else{
            coursePos = -1;
        }
        if(coursePos >= 0){
            courseSelected.setText("Course: " + User.currentUser.getCourses()[coursePos]);
        }
        else{
            courseSelected.setText("Course: ");
        }

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarview);
        startDate = calendarView.getDate();
        calendarView.setOnDateChangeListener(dateChangeListener);
    }
    
    
    CalendarView.OnDateChangeListener dateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
            if(calendarView.getDate() != startDate){ //check that a new date was selected
                String date = "";
                month++;//Calendar View starts months at 0, sql uses 1

                //update startDate
                startDate = calendarView.getDate();

                Intent intent = new Intent(getApplicationContext(), CategoryChooserActivity.class);
                intent.putExtra("coursePos", coursePos);
                intent.putExtra("dueDate", ""+year+"-"+month+"-"+dayOfMonth+" 00:00:00");
                startActivity(intent);
            }
        }
    };
    
    
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