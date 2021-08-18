package com.hamseong.hohaeng.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseRepository {

    @Query("SELECT * FROM allcourseinfo")
    List<AllCourseInfo> findAll();

    @Query("SELECT * FROM allcourseinfo where count=:id")
    AllCourseInfo findById(int id);

    @Insert
    void insert(AllCourseInfo allCourseInfo);

    @Delete
    void delete(AllCourseInfo allCourseInfo);
}
