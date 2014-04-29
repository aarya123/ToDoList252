package com.purdue.Todo;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateAssignment extends AsyncTask<Assignment, Void, Integer> {

    private Context c;

    public CreateAssignment(Context context) {
        this.c = context;
    }

    protected Integer doInBackground(Assignment... assignments) {
        Assignment assignment = assignments[0];
        String url = "http://54.213.80.211/ToDoList252/server/CreateAssignment.php"; //TODO:make not hardcoded
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            //TODO: CHECK THIS WITH ANUBHAW
            urlParameters.add(new BasicNameValuePair("Course_id", assignment.getCourse()));
            urlParameters.add(new BasicNameValuePair("due_date", assignment.getDueDate()));
            urlParameters.add(new BasicNameValuePair("categories", assignment.getCategory()));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

           // Http
        }
        catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return 0;
    }
}