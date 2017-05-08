package com.project.pop.attendancemanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.project.pop.attendancemanagement.SubjectContract.SubjectEntry;


public class Details extends AppCompatActivity {


    SharedPreferences prefs;
    EditText editText;
    SubjectDbHelper subjectDbHelper;
    private ListView mSubjectListView;
    private SubjectAdapter mSubjectAdapter;
    int firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstTime = 0;
        subjectDbHelper = new SubjectDbHelper(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.apply();
            setContentView(R.layout.first_time);

        }
        else {
            setContentView(R.layout.activity_details);
            ShowDeatilsOfSubject();
        }

    }


    public  void ShowDeatilsOfSubject(){
        mSubjectListView = (ListView)findViewById(R.id.subjectListView);
        ArrayList<Subject> subjectArrayList = new ArrayList<>();

        SQLiteDatabase database = subjectDbHelper.getReadableDatabase();

        String[] projection = {
                SubjectEntry._ID,
                SubjectEntry.COLUMN_COURSE_CODE,
                SubjectEntry.COLUMN_COURSE_TITLE,
                SubjectEntry.COLUMN_ATTENDED,
                SubjectEntry.COLUMN_MISSED };

        // Perform a query on the table
        Cursor cursor = database.query(
                SubjectEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        try {

            int idColumnIndex = cursor.getColumnIndex(SubjectEntry._ID);
            int courseCodeColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_COURSE_CODE);
            int titleColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_COURSE_TITLE);
            int attendedColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_ATTENDED);
            int missedColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_MISSED);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String courseCode = cursor.getString(courseCodeColumnIndex);
                String title = cursor.getString(titleColumnIndex);
                int attended = cursor.getInt(attendedColumnIndex);
                int missed = cursor.getInt(missedColumnIndex);
                Subject sb = new Subject(courseCode,title,attended,missed);
                subjectArrayList.add(sb);

            }
        } finally {

            cursor.close();
        }

        mSubjectAdapter = new SubjectAdapter(this, subjectArrayList);
        mSubjectListView.setAdapter(mSubjectAdapter);

        mSubjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                           int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Details.this, UpdateActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", position);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onResume(){
        super.onResume();
        if(firstTime == 1) {
            ShowDeatilsOfSubject();
        }
    }

    public void onPause(){
        super.onPause();
        firstTime = 1;
    }

    public void Next(View view){

        editText = (EditText)findViewById(R.id.name_text);
        String name = editText.getText().toString();
        if(!name.isEmpty()) {
            /*
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(getString(R.string.user_name), name.trim());
            edit.apply();
            */
            Intent i = new Intent(this, AddSubject.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT).show();
        }
    }

    public void addSubject(View view){
        Intent i = new Intent(this, AddSubject.class);
        startActivity(i);
    }

}
