package com.project.pop.attendancemanagement;

/**
 * Created by rohit on 12-04-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.pop.attendancemanagement.SubjectContract.SubjectEntry;


public class SubjectDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = SubjectDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "student.db";


    private static final int DATABASE_VERSION = 1;


    public SubjectDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_SUBJECT_TABLE =  "CREATE TABLE " + SubjectEntry.TABLE_NAME + " ("
                + SubjectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SubjectEntry.COLUMN_COURSE_CODE + " TEXT NOT NULL, "
                + SubjectEntry.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + SubjectEntry.COLUMN_ATTENDED + " INTEGER NOT NULL, "
                + SubjectEntry.COLUMN_MISSED + " INTEGER NOT NULL);" ;

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_SUBJECT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean updateData(int id,String courseCode,String title,int attend,int missed){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SubjectEntry.COLUMN_COURSE_CODE, courseCode);
        values.put(SubjectEntry.COLUMN_COURSE_TITLE, title);
        values.put(SubjectEntry.COLUMN_ATTENDED, attend);
        values.put(SubjectEntry.COLUMN_MISSED, missed);

        database.update(SubjectEntry.TABLE_NAME, values, "_id="+id, null);

        return true;

    }
}