package com.hamseong.hohaeng.model;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class AllCourseInfo implements Serializable {// 룸 디비
    @PrimaryKey(autoGenerate = true)
    private int count;

    private String name;

    private String X;

    private String Y;

    private int endYear;

    private int endMonth;

    private int endDay;

    private int startYear;

    private int startMonth;

    private int startDay;

    private String Location;

    private int people;
  
    public AllCourseInfo(){

    }
   public AllCourseInfo(String name, String x, String y, int endYear, int endMonth, int endDay, int startYear, int startMonth, int startDay, String location, int people) {
        this.name = name;
        X = x;
        Y = y;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
}
