package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button loginButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open course chooser activity
                Intent intent = new Intent(getApplicationContext(), CourseChooserActivity.class);
                startActivity(intent);
            }
        });
    }
}
