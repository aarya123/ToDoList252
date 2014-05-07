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
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskList_ArrayAdapter extends ArrayAdapter<Assignment>{
	  private final Context context;
	  ViewHolder holder;

	  public TaskList_ArrayAdapter(Context context, ArrayList<Assignment> list) {
	    super(context, R.layout.task_element_in_list, list);
	    this.context = context;
	  }

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
	        
	        //set date in proper format
	        String requiredDateFormat;
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	        requiredDateFormat = sdf2.format(dueDate);
	        
	        
	        //set textViews
	        holder.course.setText(a.searchCourse().getName());
	        holder.dueDate.setText(requiredDateFormat);
	        holder.category.setText(a.getCategories());
	        
	        
	        //set apropriate warning colors
	        if(days < 0)
	        	holder.dueIn.setTextColor(Color.parseColor("#FFFFFF")); //White
	        else if(days <= 1)
	        	holder.dueIn.setTextColor(Color.parseColor("#E80000")); //Red
	        else if(days <= 3)
	        	holder.dueIn.setTextColor(Color.parseColor("#FF9900")); //Orange
	        else
	        	holder.dueIn.setTextColor(Color.parseColor("#33CC00")); //Green
	        
	        //set the required due date text
	        /*if(minutes == 1)
	        	holder.dueIn.setText("Due in: " + minutesBetweenDates + " min");
	        else if(minutes < 60)
	        	holder.dueIn.setText("Due in: " + minutesBetweenDates + " mins");
	        else if(hours == 1)
	        	holder.dueIn.setText("Due in: " + hoursBetweenDates + " hour");*/
	        if(minutes<0)
	        	holder.dueIn.setText("Overdue!");
	        else if(hours < 24)
	        	holder.dueIn.setText("Due Today");
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
