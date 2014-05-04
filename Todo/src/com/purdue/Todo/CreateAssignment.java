package com.purdue.Todo;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            int courseid = 0;
            Course[] courses = User.currentUser.getCourses();
            for(int i = 0; i<courses.length; i++){
                if(courses[i] == assignment.getCourse()){
                    courseid = courses[i].getId();
                    break;
                }
            }
            urlParameters.add(new BasicNameValuePair("Course_id", ""+courseid));
            urlParameters.add(new BasicNameValuePair("due_date", assignment.getDueDate()));
            urlParameters.add(new BasicNameValuePair("categories", assignment.getCategory()));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);
            System.out.println("Response code: "+ response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());

        }
        catch( UnsupportedEncodingException e){
            //thrown by creating UrlEncodedFormEntity
            e.printStackTrace();
        }
        catch( IOException e ){
            //thrown by client.execute()
            e.printStackTrace();
        }
        return 0;
    }
}