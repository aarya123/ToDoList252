package com.purdue.Todo;

import android.app.Activity;

/*TODO:
Allow user ID to only contain numbers
When you type enter on the login screen, it should log in instead of typing a newline
Once you're logged in or registered you shouldn't be able to go back
I get connection refused every time I try to log in or register
 */

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        int savedUserID = settings.getInt("currentUser", -1);
        String savedUserIDString = savedUserID > -1 ? String.valueOf(savedUserID) : "";
        ((EditText) findViewById(R.id.userID)).setText(savedUserIDString);
        if (savedUserID != -1)
            login(null);

        context = this;
    }

    public static String serverURL = "http://54.213.80.211/ToDoList252/server/";

    static SplashActivity context; //This is needed for GetUserTask

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
            if (result.charAt(0) == '<') { //Error
                Toast.makeText(context, result.substring(1,result.length()-2), Toast.LENGTH_LONG).show();
            }
            else {
                Gson gson = new Gson();
                User.setCurrentUser(gson.fromJson(result, User.class));

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
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}
