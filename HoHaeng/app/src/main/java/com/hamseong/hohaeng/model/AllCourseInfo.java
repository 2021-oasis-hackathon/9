package com.hamseong.hohaeng.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AllCourseInfo implements Serializable {
    private ArrayList<AllPlaceInfo> allPlaceInfos;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int startYear;
    private int startMonth;
    private int startDay;
    private String Loaction;

    public AllCourseInfo(ArrayList<AllPlaceInfo> allPlaceInfos, int endYear, int endMonth, int endDay, int startYear, int startMonth, int startDay, String loaction) {
        this.allPlaceInfos = allPlaceInfos;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        Loaction = loaction;
    }


    public ArrayList<AllPlaceInfo> getAllPlaceInfos() {
        return allPlaceInfos;
    }

    public void setAllPlaceInfos(ArrayList<AllPlaceInfo> allPlaceInfos) {
        this.allPlaceInfos = allPlaceInfos;
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

    public String getLoaction() {
        return Loaction;
    }

    public void setLoaction(String loaction) {
        Loaction = loaction;
    }
}
