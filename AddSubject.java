package com.project.pop.attendancemanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.pop.attendancemanagement.SubjectContract.SubjectEntry;


public class AddSubject extends AppCompatActivity {


    private EditText mCourseCode;

    private EditText mCourseTitle;

    SubjectDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mCourseCode = (EditText)findViewById(R.id.courseCode);
        mCourseTitle = (EditText)findViewById(R.id.courseTitle);

    }


    public void newEntry(View view) {

        String courseCodeString = mCourseCode.getText().toString().trim();
        String titleCourseString = mCourseTitle.getText().toString().trim();
        int attended = 0;
        int missed = 0;

        // Create database helper


        // Gets the database in write mode
        mDbHelper = new SubjectDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(SubjectEntry.COLUMN_COURSE_CODE, courseCodeString);
        values.put(SubjectEntry.COLUMN_COURSE_TITLE, titleCourseString);
        values.put(SubjectEntry.COLUMN_ATTENDED, attended);
        values.put(SubjectEntry.COLUMN_MISSED, missed);


        long newRowId = db.insert(SubjectEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Subject saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

}
