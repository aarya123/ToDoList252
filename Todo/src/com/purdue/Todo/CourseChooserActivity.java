package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Sean on 4/22/14.
 */
public class CourseChooserActivity extends Activity {

    String myClasses[] = {"CHM 115", "MA 416", "CS 307", "CS 252"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_chooser);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.grid_element, myClasses);
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                TextView courseView = (TextView) view;
                String courseName = courseView.getText().toString();

                Intent intent = new Intent(getApplicationContext(), DueDateChooserActivity.class);
                intent.putExtra("courseName", courseName);
                startActivity(intent);
            }
        });

    }
}