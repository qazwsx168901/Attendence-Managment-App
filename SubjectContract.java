package com.project.pop.attendancemanagement;

/**
 * Created by rohit on 12-04-2017.
 */

import android.provider.BaseColumns;

public final class SubjectContract {


    private SubjectContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class SubjectEntry implements BaseColumns {

        public final static String TABLE_NAME = "Attendance";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_COURSE_CODE = "course_code";

        public final static String COLUMN_COURSE_TITLE = "course_title";


        public final static String COLUMN_ATTENDED = "attended";


        public final static String COLUMN_MISSED = "missed";


    }

}

