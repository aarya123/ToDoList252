package com.purdue.Todo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class EditAssignmentActivity extends Activity{

	public String[] assignment_details;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);
        assignment_details = getIntent().getStringExtra("Assignment Object Details").split("%");
        
        TextView title = (TextView) findViewById(R.id.assignment_title);
        TextView dueDate = (TextView) findViewById(R.id.due_date);

        
        //store the date in the required format
        
        //step 1: convert string to Date object
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = new Date();
        try {
        	temp = sdf1.parse(assignment_details[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        //step 2: create buffer to hold Date in required format
        String requiredDateFormat;
        
        //step 3: store the formatted date in the buffer
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEE, MMMMMMMMMM dd");
        requiredDateFormat = sdf2.format(temp);
        
        
        //set the text views
        title.setText(assignment_details[0] + " - " + assignment_details[2]);
        dueDate.setText("Due:  " + requiredDateFormat);
        
        
        //Toast.makeText(getApplicationContext(), "Due    "+requiredDateFormat, Toast.LENGTH_LONG).show();
        
        
        
    }

}
