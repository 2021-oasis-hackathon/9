package com.hamseong.hohaeng.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomCourese.class},version = 1)
public abstract class CourseAppDatabase extends RoomDatabase {
    public abstract CourseRepository courseRepository();
}
