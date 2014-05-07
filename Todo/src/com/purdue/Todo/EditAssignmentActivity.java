package com.purdue.Todo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditAssignmentActivity extends Activity{

	public String[] assignment_details;
	public String assignment_id;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_assignment);
        assignment_details = getIntent().getStringExtra("Assignment Object Details").split("%");
        assignment_id = assignment_details[3];
        
        
        TextView title = (TextView) findViewById(R.id.assignment_title);
        TextView dueDate = (TextView) findViewById(R.id.due_date);
        final EditText assignment_notes = (EditText)findViewById(R.id.notes);
        
        //if assignment has notes, display them
        if(!assignment_details[4].equals("null")){
        	assignment_notes.setText(assignment_details[4], TextView.BufferType.EDITABLE);
        }
        
        Button doneButton = (Button) findViewById(R.id.done_editing);
        
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
        
        doneButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String notes = assignment_notes.getText().toString();
				//Toast.makeText(getApplicationContext(), "Notes: "+notes, Toast.LENGTH_LONG).show();
				Assignment modifiedAssignment = Assignment.getAssignmentByID(Integer.parseInt(assignment_id));
				modifiedAssignment.setNotes(notes);
				new EditAssignmentTask(EditAssignmentActivity.this).execute(assignment_id,notes);
			}
		});
        	
        	
        
    }

}
