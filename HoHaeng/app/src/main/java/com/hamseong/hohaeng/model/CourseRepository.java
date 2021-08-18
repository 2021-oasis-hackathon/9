package com.hamseong.hohaeng.model;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CourseRepository {
    @Query("SELECT * FROM roomcourese")
    List<RoomCourese> findAll();

    @Query("SELECT * FROM roomcourese where id=:uid")
    RoomCourese findById(int uid);

    @Insert
    void insert(RoomCourese user);

    @Delete
    void delete(RoomCourese user);
}
