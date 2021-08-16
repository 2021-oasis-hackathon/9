package com.hamseong.hohaeng.model;

import com.skt.Tmap.TMapPoint;

public class TMapViewPointInfo {
    TMapPoint tMapPoint;
    String Name;

    public TMapViewPointInfo(TMapPoint tMapPoint, String name){
        this.tMapPoint = tMapPoint;
        Name = name;
    }

    public TMapPoint gettMapPoint() {
        return tMapPoint;
    }

    public void settMapPoint(TMapPoint tMapPoint) {
        this.tMapPoint = tMapPoint;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
