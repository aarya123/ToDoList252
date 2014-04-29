package com.purdue.Todo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    private String serverURL = "http://54.213.80.211/ToDoList252/server/";

    //TODO: this doesn't work yet
    /**
     * Sends a CreateUser request to the server. Currently only prints a debug message.
     *
     * @param courses
     * @param colors
     */
    public void createUser(String[] courses, int[] colors) {
        HttpClient client = new DefaultHttpClient();
        String request = serverURL + "CreateUser.php?";
        for (int i = 0; i < courses.length; i++) {
            request += "course" + i + "=" + courses[i];
            request += "color" + i + "=" + colors[i];
        }
        Toast.makeText(this, request, Toast.LENGTH_LONG).show();
        /*HttpGet get = new HttpGet(request);
        try {
            HttpResponse response = client.execute(get);
            if (response != null) {
                //decode it
            }
        } catch (ClientProtocolException e) {
            Toast.makeText(this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Caught IOException", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Caught Exception", Toast.LENGTH_SHORT).show();
        }*/
    }

    final SplashActivity context = this;

    private class GetUserTask extends AsyncTask<Integer, Void, String> {
        protected String doInBackground(Integer... params) {
            int userID;
            try {
                userID = params[0];
            }
            catch (NumberFormatException e) {
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
                }
                else {
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
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    public void getUser(final int userID) {
        new GetUserTask().execute(userID);
    }

    public void login(View view) {
        int userID = Integer.parseInt(((EditText) findViewById(R.id.userID)).getText().toString());
        getUser(userID);
    }

    public void signUp(View view) {
        String[] a = {"abc", "def", "ghi"};
        int[] b = {0xff0000, 0x00ff00, 0x0000ff};
        createUser(a, b);
    }
}
