package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sean on 4/22/14.
 */
public class CategoryChooserActivity extends Activity {
    String myCategories[] = {"Lab", "Prelab", "Homework", "Test"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_chooser);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.grid_element, myCategories);
        gridView.setAdapter(mAdapter);

        String courseName = "";
        String displayDate = "";
        TextView selectedCourse = (TextView) findViewById(R.id.selectedCourse);
        TextView selectedDueDate = (TextView) findViewById(R.id.selectedDueDate);

        if(getIntent().hasExtra("courseName")){
            courseName = getIntent().getStringExtra("courseName");
        }
        if(getIntent().hasExtra("dueDate")){
            String dateParts[] = getIntent().getStringExtra("dueDate").split("/"); //date in form month/day/year
            int month = Integer.parseInt(dateParts[0]);
            StringBuilder dateString = new StringBuilder();
            switch(month){
                case 0: dateString.append("January"); break;
                case 1: dateString.append("February"); break;
                case 2: dateString.append("March"); break;
                case 3: dateString.append("April"); break;
                case 4: dateString.append("May"); break;
                case 5: dateString.append("June"); break;
                case 6: dateString.append("July"); break;
                case 7: dateString.append("August"); break;
                case 8: dateString.append("September"); break;
                case 9: dateString.append("October"); break;
                case 10: dateString.append("November"); break;
                case 11: dateString.append("December"); break;
                default: break;
            }
            dateString.append(" ");
            dateString.append(dateParts[1]);
            displayDate = dateString.toString();
        }

        selectedCourse.setText("Course: " + courseName);
        selectedDueDate.setText("Due: "+displayDate);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                //TODO:add task to database
                //show confirmation toast -- find out how to do link to undo
                Toast.makeText(getApplicationContext(), "Assignment added", Toast.LENGTH_SHORT).show();
                //return to class chooser screen
                Intent intent = new Intent(getApplicationContext(), CourseChooserActivity.class);
                startActivity(intent);
            }
        });

    }
}