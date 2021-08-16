package com.hamseong.hohaeng.model;

import com.google.gson.annotations.SerializedName;

public class Placeinfo {
    @SerializedName("place_name")
    String place_name;             // 장소명, 업체명

    @SerializedName("category_name")
    String category_name;          // 카테고리 이름

    @SerializedName("x")
    String x;                      // X 좌표값 혹은 longitude
    @SerializedName("y")
    String y;                     // Y 좌표값 혹은 latitude

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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
}
