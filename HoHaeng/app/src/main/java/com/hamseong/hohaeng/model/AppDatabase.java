package com.hamseong.hohaeng.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Database(entities = {AllCourseInfo.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  CourseRepository courseRepository();
}



