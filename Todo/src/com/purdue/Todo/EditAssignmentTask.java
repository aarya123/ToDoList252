package com.purdue.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class EditAssignmentTask extends AsyncTask<String,Integer,String>{
Activity activity;
	public EditAssignmentTask(Activity activity){
		this.activity=activity;
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 String url = "http://54.213.80.211/ToDoList252/server/EditAssignment.php"; //TODO:make not hardcoded
		 try{
			 HttpClient client = new DefaultHttpClient();
			 HttpPost post = new HttpPost(url);

			 List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			 //TODO: CHECK THIS WITH ANUBHAW
			 urlParameters.add(new BasicNameValuePair("Assignment_id", params[0]));
			 urlParameters.add(new BasicNameValuePair("notes", params[1]));

			 post.setEntity(new UrlEncodedFormEntity(urlParameters));

			 HttpResponse response = client.execute(post);
			Log.d("Task1","Response code: "+ response.getStatusLine().getStatusCode());
			
            
			
			BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            Log.d("Task2",result.toString());
			 
		 } 
		 catch( UnsupportedEncodingException e){
	         //thrown by creating UrlEncodedFormEntity
	         e.printStackTrace();
	     }
	     catch( IOException e ){
	         //thrown by client.execute()
	         e.printStackTrace();
	     }
		 return null;

	}
	protected void onPostExecute (String result){
		activity.finish();
	}
}
