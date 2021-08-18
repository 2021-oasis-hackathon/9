package com.hamseong.hohaeng.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AllPlaceInfo implements Serializable {
    private String Name;
    private String Category;
    private String X;
    private String Y;
    private String Url;
    private String Start;
    private String Road_Address;
    private String phone;
    private ArrayList<String> tag;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getRoad_Address() {
        return Road_Address;
    }

    public void setRoad_Address(String road_Address) {
        Road_Address = road_Address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }
}
