package com.purdue.Todo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
            int courseid = -1;
            int coursePos = -1;
            Course[] courses = User.currentUser.getCourses();
            for(int i = 0; i<courses.length; i++){
                if(courses[i]==null) { Log.d("Sean", "course is null"); }
                if(courses[i].getName()==null){ Log.d("Sean", "name is null"); }
                if(assignment==null){ Log.d("Sean", "assignment is null"); }
                if(assignment.getCourse()==null){ Log.d("Sean", "assignment.course is null"); }
                if(courses[i].getName().equals(assignment.getCourse().getName())){
                    courseid = courses[i].getId();
                    coursePos = i;
                    break;
                }
            }
            urlParameters.add(new BasicNameValuePair("Course_id", ""+courseid));
            urlParameters.add(new BasicNameValuePair("due_date", assignment.getDueDate()));
            urlParameters.add(new BasicNameValuePair("categories", assignment.getCategories()));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            Log.d("Sean-url", EntityUtils.toString(post.getEntity()));
            HttpResponse response = client.execute(post);
            System.out.println("Response code: "+ response.getStatusLine().getStatusCode());
            Log.d("Sean", "Response code: "+response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            Gson gson = new Gson();
            Course newCourse = gson.fromJson(result.toString(), Course.class);
            User.currentUser.getCourses()[coursePos] = newCourse;

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