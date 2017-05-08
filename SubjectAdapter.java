package com.project.pop.attendancemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter<Subject> {


    public SubjectAdapter(Context context, ArrayList<Subject> subjects) {
        super(context, R.layout.subject_detail, subjects);
    }


    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        // check if the current view is reused else inflate the view
        if(listItemView == null){
            listItemView  = LayoutInflater.from(getContext()).inflate(R.layout.subject_detail, parent,false);
        }

        //get the object located at position
        Subject subject_item = getItem(position);

        TextView courseTitle = (TextView) listItemView.findViewById(R.id.title);
        courseTitle.setText(subject_item.getTitle());

        TextView attended = (TextView) listItemView.findViewById(R.id.attended);
        attended.setText("Attd:- "+subject_item.getAttended()+"");


        TextView percentage = (TextView) listItemView.findViewById(R.id.percentage);
        int per;
        if(subject_item.getAttended() + subject_item.getMissed() == 0){
            per = 100;
        }
        else {
            per = (100)*(subject_item.getAttended())/(subject_item.getAttended() + subject_item.getMissed());
        }
        percentage.setText(per+"%");

        LinearLayout linearLayout = (LinearLayout)listItemView.findViewById(R.id.list_background);
        if(per < 75){
            linearLayout.setBackgroundResource(R.drawable.less_attendance);
        }



        TextView missed = (TextView) listItemView.findViewById(R.id.missed);

        if(per >= 75) {
            missed.setText("Missed:- " + subject_item.getMissed());
        }
        else{
            int remain = (3*subject_item.getMissed()) - (1*subject_item.getAttended());
            missed.setText("Missed:- " + subject_item.getMissed()+"\n"+ "Attend next "+remain+" class to reach 75%");
        }



        return listItemView;
    }
}
