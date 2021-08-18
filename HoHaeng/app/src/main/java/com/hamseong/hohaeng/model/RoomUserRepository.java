package com.hamseong.hohaeng.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomUserRepository {
    @Query(("SELECT * FROM roomuser"))
    List<RoomUser> findAll();

    @Query(("SELECT * FROM roomuser where count=:count"))
    RoomUser findUserById(int count);

    @Query(("SELECT * FROM roomuser where name=:name"))
    RoomUser findUserByname(String name);


    @Insert
    void insert(RoomUser roomUser);

    @Delete
    void delete(RoomUser roomUser);

}
