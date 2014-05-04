package com.purdue.Todo;

import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.widget.Button;

public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    private String serverURL = "http://54.213.80.211/ToDoList252/server/";

    final SplashActivity context = this; //This is needed for GetUserTask
    private class CourseAndColor {
        public String course;
        public int color; //a 6-hex-digit color (e.g. 0xff0000)

        public CourseAndColor(String course, int color) {
            this.course = course;
            this.color = color;
        }
    }

    private class CreateUserTask extends AsyncTask<CourseAndColor, Void, String> {
        protected String doInBackground(CourseAndColor... courses) {
            String request = serverURL + "CreateUser.php?";
            for (int i = 0; i < courses.length; i++) {
                request += "course" + i + "=" + courses[i].course;
                request += "color" + i + "=" + courses[i].color;
            }

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(request);
            try {
                HttpResponse response = client.execute(get);
                if (response != null) {
                    InputStream content = response.getEntity().getContent();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    String responseString = "";
                    while ((s = buffer.readLine()) != null) {
                        responseString += s;
                    }
                    return responseString;
                } else {
                    return "<null response>";
                }
            } catch (Exception e) {
                Log.e("TODO list", e.toString());
                return "<" + e.toString() + ">";
            }
        }

        protected void onPostExecute(String result) {
            //TODO: handle the result
            //The result will be enclosed in <> if it's an error, otherwise it's a JSON string
            Log.i("TODO list", "Created user: " + result);
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    private class GetUserTask extends AsyncTask<Integer, Void, String> {
        protected String doInBackground(Integer... params) {
            int userID;
            try {
                userID = params[0];
            } catch (NumberFormatException e) {
                return "<Invalid number>";
            }
            HttpClient client = new DefaultHttpClient();
            String request = serverURL + "GetUser.php?User_id=" + userID;
            Log.i("TODO list", "Requesting: " + request);

            HttpGet get = new HttpGet(request);
            try {
                HttpResponse response = client.execute(get);
                if (response != null) {
                    InputStream content = response.getEntity().getContent();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    String responseString = "";
                    while ((s = buffer.readLine()) != null) {
                        responseString += s;
                    }
                    return responseString;
                } else {
                    return "<null response>";
                }
            } catch (Exception e) {
                Log.e("TODO list", e.toString());
                return "<" + e.toString() + ">";
            }
        }

        protected void onPostExecute(String result) {
            //The result will be enclosed in <> if it's an error, otherwise it's a JSON string
            Log.i("TODO list", "Got user: " + result);
            if (result.get(0) == '<') { //Error
                Toast.makeText(context, result.substring(1,result.length()-2), Toast.LENGTH_LONG).show();
            }
            else {
                User.curentUser = gson.fromJson(result, User.class);

                Intent intent = new Intent(getApplicationContext(), CourseChooserActivity.class);
                startActivity(intent);
            }
        }
    }

    //This is called when the user presses the login button
    public void login(View view) {
        int userID = Integer.parseInt(((EditText) findViewById(R.id.userID)).getText().toString());
        new GetUserTask().execute(userID);
    }

    public void signUp(View view) {
        //TODO: this should switch to a new Activity that allows the user to enter a list of courses and their colors, and then calls createUser

        //createUser(new CourseAndColor("abc", 0xff0000), new CourseAndColor("def", 0x00ff00), new CourseAndColor("ghi", 0x0000ff));
    }

    private void createUser(CourseAndColor... courses) {
        new CreateUserTask().execute(courses);
    }
}
