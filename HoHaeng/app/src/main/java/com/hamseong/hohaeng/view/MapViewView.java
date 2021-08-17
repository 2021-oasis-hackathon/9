package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hamseong.hohaeng.APIKey;
import com.hamseong.hohaeng.R;

import com.hamseong.hohaeng.model.MapData;
import com.hamseong.hohaeng.model.MapDataQuery;

import com.hamseong.hohaeng.viewmodel.MapViewModel;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord;


public class MapViewView extends AppCompatActivity implements MapView.POIItemEventListener {
    private MapViewModel mMapviewModel = new MapViewModel();//뷰모델
    private String API_Key = APIKey.KaKaoApi;//api key
    public int id = View.generateViewId();

    public static LayoutInflater inflater;//이 액티비티의 인플래터
    MapView mapView;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONES_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};//퍼미션



    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        findViewById(R.id.layout_tag).setVisibility(View.GONE);
        findViewById(R.id.layout_info).setVisibility(View.VISIBLE);
        mMapviewModel.onBalloonClick(mapView,mapPOIItem);
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//전체화면
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inflater = getLayoutInflater();


        mapView = new MapView(this);//맴뷰 객체생성
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.linearLayoutTmap);
        mapViewContainer.addView(mapView);//뷰에 추가
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        mapView.setPOIItemEventListener(this);
        mapView.setCustomCurrentLocationMarkerTrackingImage(R.drawable.cr_hohaen,new MapPOIItem.ImageOffset(18,18));//호행이 트래킹마커


        ImageView imageBDrawline = findViewById(R.id.imageB_drawline);//지도 좌우 버튼 동그랗게
        imageBDrawline.setBackground(new ShapeDrawable(new OvalShape()));
        imageBDrawline.setClipToOutline(true);
        ImageView imageView = findViewById(R.id.imageB_now);
        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);



        mMapviewModel.isLine.observe(this, new Observer<Boolean>() {//선이 그러져있는지 관찰
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    findViewById(R.id.layout_tag).setVisibility(View.VISIBLE);//태그 선택창 보이게
                } else {
                    findViewById(R.id.layout_tag).setVisibility(View.GONE);//태그 선택창 안보이게
                }
            }
        });

        mMapviewModel.mTMapmarker.observe(this, new Observer<ArrayList<MapPOIItem >>() {//마커 리스트의 변경을 관찰
            int Count;
            @Override

            public void onChanged(ArrayList<MapPOIItem > tMapMarkerItems) {
                mapView.removeAllPOIItems();//모든 마커 제거
                for(MapPOIItem mapPOIItem : tMapMarkerItems){
                    mapView.selectPOIItem(mapPOIItem, true);
                    mapView.addPOIItem(mapPOIItem);//마커 아이템 추가
                }
                mapView.refreshDrawableState();
            }
        });

        mMapviewModel.isDraw.observe(this, new Observer<Boolean>() {//선을 그리는 중인지 관찰
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    findViewById(id).setVisibility(View.VISIBLE);//지도를 덮는 뷰 보이게(지도 터치 막음)
                }else{
                    findViewById(id).setVisibility(View.INVISIBLE);//지도를 덮는 뷰 안보이게
                }
            }
        }); //선그릴때만 터치 활성화



        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//레이아웃 생성
        View view_touch = new View(this);
        view_touch.setId(id);//뷰 생성
        view_touch.setLayoutParams(layoutParams);//레이아웃 지정
        ConstraintLayout constraintMapview = findViewById(R.id.constraint_mapview);
        constraintMapview.addView(view_touch);//뷰 추가
        findViewById(id).setVisibility(View.INVISIBLE);



        imageBDrawline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapviewModel.onDrawButtonClick(mapView, view_touch);//뷰모델로 이벤트를 넘김
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MapViewView.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(MapViewView.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.i("권한 ", "얻음");
                    if(mapView.isShowingCurrentLocationMarker()){
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);

                    }else{
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
                    }
                } else {
                    ActivityCompat.requestPermissions(MapViewView.this, REQUIRED_PERMISSIONS, PERMISSIONES_REQUEST_CODE);
                }
            }
        });

        //네비게이션 바 클릭 리스너
        {

            findViewById(R.id.navi_map).setOnClickListener(new View.OnClickListener() {//다른 뷰 미완성이라 작동안함
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), TMapViewView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            findViewById(R.id.navi_main).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapViewContainer.removeViewAt(0);
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MapViewView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            findViewById(R.id.navi_like).setOnClickListener(new View.OnClickListener() {//다른 뷰 미완성이라 작동안함
                @Override
                public void onClick(View v) {
                    if (getApplicationContext() == MapViewView.this) {
                        Log.i("콘텍스트 겹침", "초기화합니다");
                        finish();
                        Intent intent = new Intent(getApplicationContext(), LikeView.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LikeView.class));
                    }
                }
            });

            findViewById(R.id.navi_info).setOnClickListener(new View.OnClickListener() {//다른 뷰 미완성이라 작동안함
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(),MyView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }


        findViewById(R.id.view_cafe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapviewModel.onTagbuttonClick();
            }
        });//임시- 태그버튼 터치 리스너(후에 바인딩해서 onclick로 넘길듯)


    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.removeAllPOIItems();//모든 마커 제거
        mapView.removeAllPolylines();
    }
}


class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
    private final View mCalloutBalloon;

    public CustomCalloutBalloonAdapter() {
        mCalloutBalloon = MapViewView.inflater.inflate(R.layout.custom_balloon, null);

    }

    @Override
    public View getCalloutBalloon(MapPOIItem poiItem) {
        ((TextView) mCalloutBalloon.findViewById(R.id.textview_Pay)).setText(poiItem.getItemName());
        return mCalloutBalloon;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem poiItem) {
        return null;
    }
}
