package com.hamseong.hohaeng.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AllPlaceInfo implements Serializable {
    private String Name;
    private String Category;
    private String X;
    private String Y;
    private String Url;
    private String Star;
    private String Value;
    private String Road_Address;
    private String phone;
    private ArrayList<String> tag;

    public AllPlaceInfo(String name, String category, String x, String y, String url, String star, String value, String road_Address, String phone, ArrayList<String> tag) {
        Name = name;
        Category = category;
        X = x;
        Y = y;
        Url = url;
        Star = star;
        Value = value;
        Road_Address = road_Address;
        this.phone = phone;
        this.tag = tag;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

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

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
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
