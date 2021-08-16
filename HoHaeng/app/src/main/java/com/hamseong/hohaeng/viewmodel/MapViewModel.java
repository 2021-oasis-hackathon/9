package com.hamseong.hohaeng.viewmodel;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.hamseong.hohaeng.APIKey;
import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.model.MapData;
import com.hamseong.hohaeng.model.MapDataQuery;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord;

public class MapViewModel {
    public MutableLiveData<Boolean> isLine = new MutableLiveData<>();
    public MutableLiveData<ArrayList<MapPOIItem>> mTMapmarker = new MutableLiveData<>();
    public MutableLiveData<Boolean> isDraw = new MutableLiveData<>();
    public ArrayList<MapPoint> alTMapPoint = new ArrayList<MapPoint>();
    int Count=0;
    int NowCount=0;


    public void onDrawButtonClick(MapView MapView, View cnvaseView){
        if(isDraw.getValue()==null|| !isDraw.getValue()){
            if(MapView.findPolylineByTag(1000)!=null) {
                MapView.removePolyline(MapView.findPolylineByTag(1000));
            }
            MapView.removeAllPOIItems();
            isLine.setValue(false);
            isDraw.setValue(true);
            if(!alTMapPoint.isEmpty()) {
                alTMapPoint.clear();
            }

            MapPolyline MapPolyLine = new MapPolyline ();;
            MapPolyLine.setLineColor(Color.BLUE);

            cnvaseView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float lastX=0,lastY=0;
                    if(isDraw.getValue()){
                        switch (event.getAction()){
                            case MotionEvent.ACTION_MOVE ://움직일시 실행
                                if(Math.sqrt(Math.pow(event.getX()-lastX,2)+Math.pow(event.getY()-lastY,2))>500){
                                    lastX=event.getX();
                                    lastY=event.getY();
                                    alTMapPoint.add( MapPoint.mapPointWithScreenLocation( lastX, lastY ));
                                }

                                break;
                            case MotionEvent.ACTION_DOWN ://터치시 실행
                                lastX=event.getX();
                                lastY=event.getY();

                                alTMapPoint.add( MapPoint.mapPointWithScreenLocation( lastX, lastY ));

                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                lastX=event.getX();
                                lastY=event.getY();
                                alTMapPoint.add( MapPoint.mapPointWithScreenLocation( lastX, lastY ));
                                for(MapPoint t : alTMapPoint){
                                    MapPolyLine.addPoint(t);
                                }
                                MapPolyLine.setTag(1000);
                                MapView.addPolyline(MapPolyLine);
                                isDraw.setValue(false);
                                isLine.setValue(true);

                                break;
                        }
                    }
                    return true;
                }
            });
        }else{
            isDraw.setValue(false);
        }
    }


    public void onBalloonClick(MapView mapView,MapPOIItem poiItem){
        System.out.println("");
    }

    public void onTagbuttonClick(){
        Count = 0;
        NowCount = 0;
        RetrofitClient retrofitClient = new RetrofitClient();
        ArrayList<MapPOIItem > tMapMarkerItems = new ArrayList<>();

        for (MapPoint point : alTMapPoint) {

            Call<MapData> call = retrofitClient.apiService.getPost(APIKey.KaKaoApi, "카페", "CE7", Double.toString(point.getMapPointGeoCoord().longitude),Double.toString(point.getMapPointGeoCoord().latitude), 4000, 1);

            call.enqueue(new Callback<MapData>() {
                @Override
                public void onResponse(Call<MapData> call, Response<MapData> response) {
                    if (response.isSuccessful()) {
                        if(tMapMarkerItems.size()==0) {
                            Log.i("url",response.body().documents.get(0).getUrl());
                            MapPOIItem marker = new MapPOIItem();
                            marker.setItemName("10000");
                            marker.setTag(Count);
                            marker.setMapPoint(mapPointWithGeoCoord(Double.parseDouble(response.body().documents.get(0).getY()), Double.parseDouble(response.body().documents.get(0).getX())));
                            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
                            marker.setCustomImageResourceId(R.drawable.marker_sel); // 마커 이미지.
                            marker.setCustomImageAutoscale(false);
                            tMapMarkerItems.add(marker);
                            NowCount++;
                        }else if((Math.abs(Double.parseDouble(response.body().documents.get(0).getY())-tMapMarkerItems.get(NowCount-1).getMapPoint().getMapPointGeoCoord().latitude)>0.0001
                                || Math.abs(Double.parseDouble(response.body().documents.get(0).getX())-tMapMarkerItems.get(NowCount-1).getMapPoint().getMapPointGeoCoord().longitude)>0.0001)){

                            Log.i("test",Double.toString(Math.abs(Double.parseDouble(response.body().documents.get(0).getY())-tMapMarkerItems.get(NowCount-1).getMapPoint().getMapPointGeoCoord().latitude)));
                            MapPOIItem marker = new MapPOIItem();
                            marker.setItemName(Integer.toString(((Count+1)*10000)));
                            marker.setTag(Count);
                            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
                            marker.setCustomImageResourceId(R.drawable.marker_sel); // 마커 이미지.
                            marker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
                            marker.setMapPoint(mapPointWithGeoCoord(Double.parseDouble(response.body().documents.get(0).getY()), Double.parseDouble(response.body().documents.get(0).getX())));

                            tMapMarkerItems.add(marker);
                            NowCount++;
                        }
                        Count++;
                        if(Count==alTMapPoint.size()){
                            mTMapmarker.setValue(tMapMarkerItems);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MapData> call, Throwable t) {

                }
            });
        }
    }


}



class RetrofitClient {//레트로핏 객체 생성
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIKey.KaKaoUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public MapDataQuery apiService = retrofit.create(MapDataQuery.class);
}