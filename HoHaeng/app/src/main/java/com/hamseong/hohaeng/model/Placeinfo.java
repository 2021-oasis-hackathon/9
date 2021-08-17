package com.hamseong.hohaeng.model;

import com.google.gson.annotations.SerializedName;

public class Placeinfo {
    @SerializedName("place_name")
    String place_name;             // 장소명, 업체명

    @SerializedName("category_name")
    String category_name;          // 카테고리 이름

    @SerializedName("phone")
    String phone;

    @SerializedName("road_address_name")
    String road_address;
    @SerializedName("x")
    String x;                      // X 좌표값 혹은 longitude
    @SerializedName("y")
    String y;                     // Y 좌표값 혹은 latitude

    @SerializedName("url")
    String url;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoad_address() {
        return road_address;
    }

    public void setRoad_address(String road_address) {
        this.road_address = road_address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
