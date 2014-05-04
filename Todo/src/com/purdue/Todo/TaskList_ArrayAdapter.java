package com.purdue.Todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskList_ArrayAdapter extends ArrayAdapter<Assignment>{
	  private final Context context;
	  //private final String[] values;
	  //Assignment assignment;
	  ViewHolder holder;

	  public TaskList_ArrayAdapter(Context context, ArrayList<Assignment> list) {
	    super(context, R.layout.task_element_in_list, list);
	    this.context = context;
	    //this.values = values;
	  }

	 /* @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  
		View rowView = inflater.inflate(R.layout.task_element_in_list, parent, false);
	    //TextView textView = (TextView) rowView.findViewById(R.id.label);
	    //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    //textView.setText(values[position]);
	    // Change the icon for Windows and iPhone
	    /*String s = values[position];
	    if (s.startsWith("Windows7") || s.startsWith("iPhone")
	        || s.startsWith("Solaris")) {
	      imageView.setImageResource(R.drawable.no);
	    } else {
	      imageView.setImageResource(R.drawable.ok);
	    }
	    
	    if (convertView == null) {
	    	LayoutInflater inflater = (LayoutInflater) getContext()
	    			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	convertView = inflater.inflate(R.layout.task_element_in_list, parent, false);
	    	assignment = new Assignment();
	    	assignment.mCourse = (ImageView) convertView.findViewById(R.id.event_image);
	    	assignment.eventName = (TextView) convertView.findViewById(R.id.event_name);
	    	assignment.eventTime = (TextView) convertView.findViewById(R.id.event_time);
	    	assignment.eventDescription = (TextView) convertView.findViewById(R.id.event_location);
	    	convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder) convertView.getTag();
	    }
	    Event e = getItem(position);
	    assignment.eventName.setText(e.getEventName());
	    assignment.eventTime.setText(e.getDatetime());
	    assignment.eventDescription.setText(e.getEventDescription());
	    assignment.eventImage.setImageResource(R.drawable.purdue_symbol);
	    return convertView;

	    return rowView;
	  }*/
	  
	  
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) getContext()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.task_element_in_list, parent, false);
	            holder = new ViewHolder();
	            holder.course = (TextView) convertView.findViewById(R.id.course_name);
	            holder.dueDate = (TextView) convertView.findViewById(R.id.due_date);
	            holder.dueIn = (TextView) convertView.findViewById(R.id.due_in);
	            holder.category = (TextView) convertView.findViewById(R.id.course_category);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        
	        Assignment a = getItem(position);
	        holder.course.setText(a.getCourse());
	        holder.dueDate.setText(a.getDueDate());
	        holder.category.setText(a.getCategory());
	        
	        //get the required number of days/hours/minutes between the due date and the current date
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date dueDate=new Date();
	        try {
				dueDate = sdf.parse(a.getDueDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        Date currentDate = new Date();
	        int days = (int) ((dueDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24));
	        int hours = (int) ((dueDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60));
	        int minutes = (int) ((dueDate.getTime() - currentDate.getTime()) / (1000 * 60));
	        
	        String daysBetweenDates = Integer.toString(days);
	        String hoursBetweenDates = Integer.toString(hours);
	        String minutesBetweenDates = Integer.toString(minutes);
	        
	        
	        //set apropriate warning colors
	        if(days <= 1)
	        	holder.dueIn.setTextColor(Color.parseColor("#E80000")); //Red
	        else if(days <= 3)
	        	holder.dueIn.setTextColor(Color.parseColor("#FF9900")); //Orange
	        else
	        	holder.dueIn.setTextColor(Color.parseColor("#33CC00")); //Green
	        
	        //set the required due date text
	        if(minutes == 1)
	        	holder.dueIn.setText("Due in: " + minutesBetweenDates + " min");
	        else if(minutes < 60)
	        	holder.dueIn.setText("Due in: " + minutesBetweenDates + " mins");
	        else if(hours == 1)
	        	holder.dueIn.setText("Due in: " + hoursBetweenDates + " hour");
	        else if(hours < 24)
	        	holder.dueIn.setText("Due in: " + hoursBetweenDates + " hours");
	        else if(days == 1)
	        	holder.dueIn.setText("Due in: " + daysBetweenDates + " day");
	        else
	        	holder.dueIn.setText("Due in: " + daysBetweenDates + " days");
	        
	        
	        return convertView;
	    }
	   
	   
	    public static class ViewHolder {
	        public TextView course, dueDate, dueIn, category;
	    }
}
