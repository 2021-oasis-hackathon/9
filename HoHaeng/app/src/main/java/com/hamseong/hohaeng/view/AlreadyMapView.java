package com.hamseong.hohaeng.view;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hamseong.hohaeng.APIKey;
import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.RecommendInfo;
import com.hamseong.hohaeng.databinding.ActivityAlreadyMapViewBinding;
import com.hamseong.hohaeng.dummy;
import com.hamseong.hohaeng.model.MapData;
import com.hamseong.hohaeng.model.MapDataQuery;
import com.hamseong.hohaeng.model.Placeinfo;
import com.hamseong.hohaeng.viewmodel.AlreadyMapViewModel;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord;

public class AlreadyMapView extends AppCompatActivity  {
/* implements MapView.POIItemEventListener

    ActivityAlreadyMapViewBinding binding;
    AlreadyMapViewModel alreadyMapViewModel = new AlreadyMapViewModel();
    MapView mapView;
    boolean isType;
    RecommendInfo recommendInfo;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONES_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};//퍼미션

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_already_map_view);


        mapView = new MapView(this);//맴뷰 객체생성
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.linearLayoutTmap);
        mapViewContainer.addView(mapView);//뷰에 추가
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        mapView.setPOIItemEventListener(this);
        mapView.setCustomCurrentLocationMarkerTrackingImage(R.drawable.cr_hohaen, new MapPOIItem.ImageOffset(18, 18));//호행이 트래킹마커

        Intent infomation = getIntent();
        if (infomation.getExtras().getString("who").equals("other")) {//남이 짠것
            isType = true;
            //쿼리

            binding.alImageBNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.alImageBNow.setVisibility(View.GONE);
                    binding.layoutContextInfo.setVisibility(View.VISIBLE);

                    binding.courseinfoName.setText();
                    binding.courseinfoUser.setText();
                    binding.courseinfoContext.setText();
                    binding.courseinfoStar.setText();

                }
            });

            binding.courseinfoPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //쿼리
                }
            });


        } else {
            isType = false;//추천으로 짠것
            recommendInfo = (RecommendInfo) infomation.getSerializableExtra("setting");

            alreadyMapViewModel.mapPointList.observe(this, new Observer<ArrayList<dummy>>() {
                int NowCount = 0;

                @Override
                public void onChanged(ArrayList<dummy> dummys) {

                    RetrofitClient2 retrofitClient = new RetrofitClient2();

                    for (dummy point : dummys) {

                        Call<MapData> call = retrofitClient.apiService.getInfo(APIKey.KaKaoApi, point.getName(), point.getX, point.getY, 1);

                        call.enqueue(new Callback<MapData>() {
                            @Override
                            public void onResponse(Call<MapData> call, Response<MapData> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().documents.size() == 0) {
                                        alreadyMapViewModel.placeinfos.add(response.body().documents.get(0));
                                        Log.i("test", Double.toString(Math.abs(Double.parseDouble(response.body().documents.get(0).getY()) - tMapMarkerItems.get(NowCount - 1).getMapPoint().getMapPointGeoCoord().latitude)));
                                        MapPOIItem marker = new MapPOIItem();
                                        marker.setItemName(point.getVlaue());
                                        marker.setTag(NowCount);
                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
                                        marker.setCustomImageResourceId(R.drawable.marker_sel); // 마커 이미지.
                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
                                        marker.setMapPoint(mapPointWithGeoCoord(Double.parseDouble(response.body().documents.get(0).getY()), Double.parseDouble(response.body().documents.get(0).getX())));
                                        mapView.addPOIItem(marker);
                                        NowCount++;
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<MapData> call, Throwable t) {
                                System.out.println("");
                            }
                        });
                    }
                }
            });

            binding.alImageBNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapPoint start = MapPoint.mapPointWithScreenLocation(0, 0);

                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                    MapPoint end = MapPoint.mapPointWithScreenLocation(displayMetrics.widthPixels, displayMetrics.heightPixels);

                    alreadyMapViewModel.recommendButtionClick(start.getMapPointGeoCoord().longitude, start.getMapPointGeoCoord().latitude, end.getMapPointGeoCoord().longitude, end.getMapPointGeoCoord().latitude, recommendInfo);
                }
            });


        }
    }

        @Override
        public void onBackPressed() {

            if (!isType) {
                if (findViewById(R.id.layout_context_info).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.layout_context_info).setVisibility(View.GONE);
                    findViewById(R.id.al_imageB_now).setVisibility(View.VISIBLE);
                } else {
                    super.onBackPressed();
                }
            } else {
                if (findViewById(R.id.layout_info).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.layout_info).setVisibility(View.GONE);
                    findViewById(R.id.al_imageB_now).setVisibility(View.VISIBLE);
                } else {
                super.onBackPressed();
            }
            }
        }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        if(!isType){
            int i = (Integer)mapPOIItem.getTag();
            findViewById(R.id.layout_info).setVisibility(View.VISIBLE);
            findViewById(R.id.al_imageB_now).setVisibility(View.GONE);
            Placeinfo placeinfo = alreadyMapViewModel.placeinfos.get(i);
            binding.infoAddress.setText("주소\n"+placeinfo.getRoad_address());
            binding.infoHash.setText();//
            binding.infoName.setText(placeinfo.getPlace_name());
            binding.infoValue.setText();//
            //쿼리 좋아요 여부
            binding.infoLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            binding.infoCall.setText("전화번호\n"+placeinfo.getPhone());
        }
    }


    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}


class RetrofitClient2 {//레트로핏 객체 생성
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIKey.KaKaoUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public MapDataQuery apiService = retrofit.create(MapDataQuery.class);

 */
}