package com.hamseong.hohaeng.model;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

public class AllCourseInfo implements Serializable {// 룸 디비
    private ArrayList<AllPlaceInfo> placeInfos;

    private int endYear;

    private int endMonth;

    private int endDay;

    private int startYear;

    private int startMonth;

    private int startDay;

    private String Location;

    private int people;

    public AllCourseInfo(ArrayList<AllPlaceInfo> placeInfos, int endYear, int endMonth, int endDay, int startYear, int startMonth, int startDay, String location, int people) {
        this.placeInfos = placeInfos;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        Location = location;
        this.people = people;
    }

    public ArrayList<AllPlaceInfo> getPlaceInfos() {
        return placeInfos;
    }

    public void setPlaceInfos(ArrayList<AllPlaceInfo> placeInfos) {
        this.placeInfos = placeInfos;
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
}
