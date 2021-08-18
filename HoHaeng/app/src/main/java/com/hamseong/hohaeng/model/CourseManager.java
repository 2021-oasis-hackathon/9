package com.hamseong.hohaeng.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CourseManager {
    static final String db_name = "Course.db";
    static final String table_name = "Courses";
    static final int version =1;


    Context myContext = null;

    private static CourseManager myDBManager = null;
    private SQLiteDatabase mydatabase = null;

    public static CourseManager getInstance(Context context)
    {
        if(myDBManager == null)
        {
            myDBManager = new CourseManager(context);
        }

        return myDBManager;
    }

    private CourseManager(Context context)
    {
        myContext = context;

        //DB Open
        mydatabase = context.openOrCreateDatabase(db_name, context.MODE_PRIVATE,null);

        //Table 생성
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " + table_name +
                "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "info ARRAYLIST<ALLMAP>"+
                "startDay INTEGER," +
                "startMonth INTEGER," +
                "startYear INTEGER," +
                "endDay INTEGER," +
                "endMonth INTEGER," +
                "endYear INTEGER," +
                "Loaction TEXT);");
    }
}
