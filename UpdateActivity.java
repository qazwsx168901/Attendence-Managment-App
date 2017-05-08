package com.project.pop.attendancemanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText editText;
    SubjectDbHelper subjectDbHelper;
    int position = -1;
    int attd,mssd;
    String crseCode,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        subjectDbHelper = new SubjectDbHelper(this);

        Bundle b = getIntent().getExtras();
        if(b != null)
            position = b.getInt("key") +1;

        //ShowDeatilsOfSubject();
        SQLiteDatabase dbw = subjectDbHelper.getReadableDatabase();

        String[] projection = {
                SubjectContract.SubjectEntry._ID,
                SubjectContract.SubjectEntry.COLUMN_COURSE_CODE,
                SubjectContract.SubjectEntry.COLUMN_COURSE_TITLE,
                SubjectContract.SubjectEntry.COLUMN_ATTENDED,
                SubjectContract.SubjectEntry.COLUMN_MISSED };

        // Perform a query on the table
        Cursor cursor = dbw.query(
                SubjectContract.SubjectEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        try {

            int idColumnIndex = cursor.getColumnIndex(SubjectContract.SubjectEntry._ID);
            int courseCodeColumnIndex = cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_COURSE_CODE);
            int titleColumnIndex = cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_COURSE_TITLE);
            int attendedColumnIndex = cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_ATTENDED);
            int missedColumnIndex = cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_MISSED);
            int currentID;
            int attended;
            int missed;
            String courseCode,titleCourse;

            while (cursor.moveToNext()) {

                currentID = cursor.getInt(idColumnIndex);
                courseCode = cursor.getString(courseCodeColumnIndex);
                titleCourse = cursor.getString(titleColumnIndex);
                attended = cursor.getInt(attendedColumnIndex);
                missed = cursor.getInt(missedColumnIndex);

                if(currentID == position){
                    attd = attended;
                    mssd = missed;
                    crseCode = courseCode;
                    title = titleCourse;
                    break;
                }
                //Toast.makeText(this,currentID+courseCodeColumnIndex+title+attended+missed,Toast.LENGTH_SHORT).show();
            }
        } finally {

            cursor.close();
        }


    }

    public void UpdateAttendance(View view){
         switch (view.getId()){
             case R.id.attended_button:
                 //increase attended
                 attd +=1;
                 subjectDbHelper.updateData(position,crseCode,title,attd,mssd);
                 Toast.makeText(this,"Attendance Taken for "+title,Toast.LENGTH_SHORT).show();
                 break;

             case R.id.missed_button:
                 //increase missed
                 mssd += 1;
                 subjectDbHelper.updateData(position,crseCode,title,attd,mssd);
                 Toast.makeText(this,"Attendance Taken for "+title,Toast.LENGTH_SHORT).show();
                 break;
         }
    }

}
