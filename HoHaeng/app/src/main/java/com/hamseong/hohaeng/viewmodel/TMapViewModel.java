package com.hamseong.hohaeng.viewmodel;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.hamseong.hohaeng.model.TMapViewPointInfo;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TMapViewModel {
    public MutableLiveData<ArrayList<TMapViewPointInfo>> mTMapPoints = new MutableLiveData<>();


    public ArrayList<TMapViewPointInfo> tMapPoints = new ArrayList<>();

    public void viewCreate(TMapView tMapView){
        //서버 쿼리
        tMapPoints.add(new TMapViewPointInfo(new TMapPoint(37.50255157069766, 127.01348748265056),"스타벅스"));
        tMapPoints.add(new TMapViewPointInfo(new TMapPoint(37.49453585999827, 127.01081305716644),"쌍교"));
        tMapPoints.add(new TMapViewPointInfo(new TMapPoint(37.55675289428698, 127.0280482436196),"네이버"));
        tMapPoints.add(new TMapViewPointInfo(new TMapPoint(37.53201369785026, 126.99001197006784),"전남대"));
        mTMapPoints.setValue(tMapPoints);
    }

    public TMapPolyLine getroad(Pair<TMapPoint,TMapPoint> point) {
        TMapPolyLine polyLine = null;
        try {
            AsyncTask<Pair<TMapPoint, TMapPoint>, Void, TMapPolyLine> asyncTask = new AsyncTask<Pair<TMapPoint, TMapPoint>, Void, TMapPolyLine>() {
                @Override
                protected TMapPolyLine doInBackground(Pair<TMapPoint, TMapPoint>... pairs) {
                    TMapPolyLine tMapPolyLine = null;
                    try {
                        tMapPolyLine = new TMapData().findPathData(point.first, point.second);
                        tMapPolyLine.setLineColor(Color.BLUE);
                        tMapPolyLine.setLineWidth(2);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return tMapPolyLine;
                }
            };
             polyLine= asyncTask.execute(point).get();

        }
        catch (Exception e) {

        }
        return polyLine;
    }
}