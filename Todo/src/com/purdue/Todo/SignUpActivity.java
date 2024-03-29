package com.purdue.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Created by Nathan on 5/3/2014.
 */
public class SignUpActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        courses = new ArrayList();
    }

    private ArrayList<String> courses;

    public void addCourse(View view) {
        courses.add(((EditText) findViewById(R.id.newCourse)).getText().toString());
        ((EditText) findViewById(R.id.newCourse)).setText("");

        ListView listView = (ListView) findViewById(R.id.listview);
        String coursesRev[] = new String[courses.size()];
        for (int i = 0; i < courses.size(); i++)
            coursesRev[courses.size()-i-1] = courses.get(i);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.signup_element, coursesRev);
        listView.setAdapter(mAdapter);
    }

    public void signUp(View view) {
        CourseAndColor courses2[] = new CourseAndColor[courses.size()];
        for (int i = 0; i < courses.size(); i++)
            courses2[i] = new CourseAndColor(courses.get(i), 0xffffff);
        createUser(courses2);
    }

    private class CourseAndColor {
        public String course;
        public int color; //a 6-hex-digit color (e.g. 0xff0000)

        public CourseAndColor(String course, int color) {
            this.course = course;
            this.color = color;
        }
    }

    final SignUpActivity context = this; //This is needed for CreateUserTask

    private void createUser(CourseAndColor... courses) {
        new CreateUserTask().execute(courses);
    }

    private class CreateUserTask extends AsyncTask<CourseAndColor, Void, String> {
        protected String doInBackground(CourseAndColor... courses) {
            try {
                HttpClient client = new DefaultHttpClient();
                String url = SplashActivity.serverURL + "CreateUser.php?";
                HttpPost post = new HttpPost(url);

                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();


                for (int i = 0; i < courses.length; i++) {
                    urlParameters.add(new BasicNameValuePair("Course"+i, courses[i].course));
                }

                post.setEntity(new UrlEncodedFormEntity(urlParameters));
                Log.i("Signup post entities", EntityUtils.toString(post.getEntity()));

                HttpResponse response = client.execute(post);
                Log.i("Signup response code", String.valueOf(response.getStatusLine().getStatusCode()));

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
            Log.i("TODO list", "Created user: " + result);

            if (result.charAt(0) == '<') { //Error
                Toast.makeText(context, result.substring(1,result.length()-1), Toast.LENGTH_LONG).show();
            }
            else {
                Gson gson = new Gson();
                User.setCurrentUser(gson.fromJson(result, User.class));

                Intent intent = new Intent(getApplicationContext(), CourseChooserActivity.class);
                startActivity(intent);
            }
        }
    }
}
