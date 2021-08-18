package com.hamseong.hohaeng.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {RoomUser.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  RoomUserRepository roomUserRepository();
}
