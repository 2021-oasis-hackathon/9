package com.hamseong.hohaeng.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomCourese {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String x;
    private String y;
    private String name;
    private int endYear;

    private int endMonth;

    private int endDay;

    private int startYear;

    private int startMonth;

    private int startDay;

    private String Location;

    private int people;

    public RoomCourese(){

    }
    public RoomCourese(String x, String y, String name, int endYear, int endMonth, int endDay, int startYear, int startMonth, int startDay, String location, int people) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        Location = location;
        this.people = people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
