package com.hamseong.hohaeng.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.hamseong.hohaeng.RecommendInfo;
import com.hamseong.hohaeng.model.AllPlaceInfo;
import com.hamseong.hohaeng.model.Placeinfo;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import java.util.ArrayList;

public class AlreadyMapViewModel {

    public MutableLiveData<ArrayList<AllPlaceInfo>> mapPointList = new MutableLiveData<>();
    public ArrayList<AllPlaceInfo> AllPlaceInfo;

    public void recommendButtionClick(Double StartX, Double StartY, Double EndX, Double EndY, RecommendInfo recommendInfo) {
        //쿼리 화면상의 마커 정보들

    }

}
