package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hamseong.hohaeng.APIKey;
import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityTmapViewBinding;
import com.hamseong.hohaeng.model.TMapViewPointInfo;
import com.hamseong.hohaeng.viewmodel.TMapViewModel;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.HashMap;

public class TMapViewView extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {


    TMapView tMapView;
    TMapViewModel mTMapViewModel = new TMapViewModel();
    ActivityTmapViewBinding binding;
    TMapTapi tMapTapi;
    TMapPoint nowLo;
    TMapGpsManager tmapgps;
    boolean nowTracking=false;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONES_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};//퍼미션

    @Override
    public void onLocationChange(Location location) {
        nowLo = tmapgps.getLocation();
        tMapView.removeMarkerItem("Loaction");
        TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
        tMapMarkerItem.setPosition(0.5f, 0.5f); // 마커의 중심점을 중앙, 하단으로 설정
        tMapMarkerItem.setTMapPoint( nowLo); // 마커의 좌표 지정
        tMapMarkerItem.setName("location"); // 마커의 타이틀 지정
        Bitmap bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.cr_hohaen);
        tMapMarkerItem.setIcon(bitmap); // 마커 아이콘 지정
        tMapView.addMarkerItem("Location", tMapMarkerItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tmap_view);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//전체화면
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imageView = findViewById(R.id.Tmap_imageB_now);
        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);


        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(APIKey.TmapApi);

        LinearLayout linerLayout = findViewById(R.id.linearLayoutmap);
        linerLayout.addView(tMapView);
        tmapgps = new TMapGpsManager(this);

        //Bitmap bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(),R.drawable.cr_hohaen);
        //tMapView.setIcon(bitmap);

        mTMapViewModel.viewCreate(tMapView);

        mTMapViewModel.mTMapPoints.observe(this, new Observer<ArrayList<TMapViewPointInfo>>() {

            int Count= 1;

            @Override
            public void onChanged(ArrayList<TMapViewPointInfo> pointInfos) {

                binding.slideList.removeAllViewsInLayout();
                Count=1;
                int dpbase = (int)getResources().getDimension(R.dimen.base);
                tMapView.removeAllTMapPolyLine();
                tMapView.removeAllMarkerItem();
                for(TMapViewPointInfo info : pointInfos){
                    if(Count!=pointInfos.size()){
                        TMapPolyLine tMapPolyLine = mTMapViewModel.getroad(new Pair<TMapPoint, TMapPoint>(pointInfos.get(Count-1).gettMapPoint(),pointInfos.get(Count).gettMapPoint()));
                        tMapView.addTMapPolyLine(Integer.toString(Count),tMapPolyLine);
                    }
                    TMapMarkerItem markerItem1 = new TMapMarkerItem();

                    markerItem1.setPosition(0.5f, 0.5f); // 마커의 중심점을 중앙, 하단으로 설정
                    markerItem1.setTMapPoint( info.gettMapPoint() ); // 마커의 좌표 지정
                    markerItem1.setName(info.getName()); // 마커의 타이틀 지정





                    ImageView line = new ImageView(TMapViewView.this);
                    line.setImageResource(R.drawable.myview_line);
                    binding.slideList.addView(line);

                    LinearLayout linearLayout = new LinearLayout(TMapViewView.this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    int size = (int)getResources().getDimension(R.dimen.tmapview_list);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, size);
                    linearLayout.setLayoutParams(layoutParams);

                    Bitmap bitmap;
                    ImageView numbericon = new ImageView(TMapViewView.this);
                    switch (Count){
                        case 1:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_1);
                            bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.tmapview_marker1);
                            markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                            break;
                        case 2:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_2);
                            bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.tmapview_marker4);

                            markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                            break;
                        case 3:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_3);
                            bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.tmapview_marker3);

                            markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                            break;
                        case 4:
                        default:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_4);
                            bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.tmapview_marker2);

                            markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                            break;
                    }
                    tMapView.addMarkerItem(Integer.toString(Count), markerItem1); // 지도에 마커 추가

                    numbericon.setPadding(dpbase*10,dpbase*23,dpbase*3,dpbase*23);
                    LinearLayout.LayoutParams viewpara = new LinearLayout.LayoutParams(70*dpbase, LinearLayout.LayoutParams.MATCH_PARENT);
                    linearLayout.addView(numbericon,viewpara);



                    viewpara = new LinearLayout.LayoutParams(100*dpbase, LinearLayout.LayoutParams.MATCH_PARENT);
                    TextView textView = new TextView(TMapViewView.this);
                    textView.setText(info.getName());
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                    textView.setTextSize(dpbase*4);
                    textView.setPadding(dpbase*3,dpbase*10,dpbase*3,dpbase*10);
                    linearLayout.addView(textView,viewpara);


                    viewpara = new LinearLayout.LayoutParams(70*dpbase, LinearLayout.LayoutParams.MATCH_PARENT);
                    ImageView car = new ImageView(TMapViewView.this);
                    car.setTag(Count);
                    car.setPadding(dpbase*3,dpbase*10,dpbase*40,dpbase*10);
                    car.setImageResource(R.drawable.tmapview_car);
                    linearLayout.addView(car,viewpara);


                    viewpara = new LinearLayout.LayoutParams(54*dpbase, LinearLayout.LayoutParams.MATCH_PARENT);
                    LinearLayout updown = new LinearLayout(TMapViewView.this);
                    updown.setOrientation(LinearLayout.VERTICAL);
                    updown.setPadding(dpbase*40,0,0,0);

                    LinearLayout.LayoutParams viewpara2 = new LinearLayout.LayoutParams(16*dpbase, 35*dpbase);
                    ImageView upbutton = new ImageView(TMapViewView.this);
                    upbutton.setImageResource(R.drawable.tmapview_upbutton);
                    upbutton.setTag(Count);
                    upbutton.setPadding(0,10*dpbase,2*dpbase,5*dpbase);



                    ImageView downbutton = new ImageView(TMapViewView.this);
                    downbutton.setImageResource(R.drawable.tmapview_downbutton);
                    downbutton.setPadding(0,5*dpbase,2*dpbase,10*dpbase);
                    downbutton.setTag(Count);

                    updown.addView(upbutton,viewpara2);
                    updown.addView(downbutton,viewpara2);

                    linearLayout.addView(updown,viewpara);


                    viewpara = new LinearLayout.LayoutParams(dpbase*60, LinearLayout.LayoutParams.MATCH_PARENT);
                    ImageView delbutton = new ImageView(TMapViewView.this);
                    delbutton.setImageResource(R.drawable.tmapview_del);
                    delbutton.setTag(Count);
                    delbutton.setPadding(dpbase*10,dpbase*26,dpbase*10,dpbase*26);
                    linearLayout.addView(delbutton,viewpara);

                    binding.slideList.addView(linearLayout);
                    Count++;

                    upbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<TMapViewPointInfo> changeinfo= new ArrayList<>();
                            int num = (Integer)v.getTag();
                           if(num!=1){
                               for(int i = 1 ;i<=mTMapViewModel.mTMapPoints.getValue().size();i++){
                                   if(i+1==num){
                                       changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i));
                                       changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i-1));
                                       i++;
                                   }else{
                                       changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i-1));
                                   }
                               }
                               mTMapViewModel.mTMapPoints.setValue(changeinfo);
                           }
                        }
                    });

                    downbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<TMapViewPointInfo> changeinfo= new ArrayList<>();
                            int num = (Integer)v.getTag();

                            if(num!=mTMapViewModel.mTMapPoints.getValue().size()-1){
                                for(int i = 1;i<mTMapViewModel.mTMapPoints.getValue().size();i++){
                                    if(i == num){
                                        changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i));
                                        changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i-1));
                                        i++;
                                    }else{
                                        changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i-1));
                                    }
                                }
                                if(num!=mTMapViewModel.mTMapPoints.getValue().size()-1){
                                    changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(mTMapViewModel.mTMapPoints.getValue().size()-1));
                                }
                                mTMapViewModel.mTMapPoints.setValue(changeinfo);
                            }
                        }
                    });

                    delbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<TMapViewPointInfo> changeinfo= new ArrayList<>();
                            int num = (Integer)v.getTag();


                            for(int i = 1;i<=mTMapViewModel.mTMapPoints.getValue().size();i++){
                                if(i != num){
                                    changeinfo.add(mTMapViewModel.mTMapPoints.getValue().get(i-1));
                                }
                            }
                            mTMapViewModel.mTMapPoints.setValue(changeinfo);
                        }
                    });

                    car.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int tag = (int) v.getTag();
                            tMapTapi = new TMapTapi(TMapViewView.this);
                            if (tMapTapi.isTmapApplicationInstalled()) {
                                if (tag == 1) {
                                    tMapTapi.invokeNavigate(mTMapViewModel.mTMapPoints.getValue().get(0).getName(),
                                            (float)mTMapViewModel.mTMapPoints.getValue().get(tag-1).gettMapPoint().getLongitude(),(float)mTMapViewModel.mTMapPoints.getValue().get(tag-1).gettMapPoint().getLatitude(),0,true);
                                } else {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("rGoName", mTMapViewModel.mTMapPoints.getValue().get(tag - 1).getName());
                                    hashMap.put("rGoX",Double.toString(mTMapViewModel.mTMapPoints.getValue().get(tag-2).gettMapPoint().getLongitude()));
                                    hashMap.put("rGoY",Double.toString(mTMapViewModel.mTMapPoints.getValue().get(tag-2).gettMapPoint().getLatitude()));

                                    hashMap.put("rStName", "출발지");
                                    hashMap.put("rStX", Double.toString(mTMapViewModel.mTMapPoints.getValue().get(tag - 1).gettMapPoint().getLongitude()));
                                    hashMap.put("rStY", Double.toString(mTMapViewModel.mTMapPoints.getValue().get(tag - 1).gettMapPoint().getLatitude()));
                                    tMapTapi.invokeRoute(hashMap);
                                }
                            }else{
                                Toast myToast = Toast.makeText(TMapViewView.this,"Tmap이 설치 되어 있지 않습니다.", Toast.LENGTH_SHORT);
                                myToast.show();
                            }
                        }
                    });
                }
            }
        });

        binding.TmapImageBNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TMapViewView.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(TMapViewView.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.i("권한 ", "얻음");
                    if(!nowTracking){
                        tmapgps.setMinTime(1000);
                        tmapgps.setMinDistance(5);
                        tmapgps.setProvider(TMapGpsManager.GPS_PROVIDER);
                        tmapgps.OpenGps();
                        nowTracking = true;

                    }else{
                        tmapgps.CloseGps();
                        nowTracking= false;
                        tMapView.removeMarkerItem("Loaction");
                    }
                } else {
                    ActivityCompat.requestPermissions(TMapViewView.this, REQUIRED_PERMISSIONS, PERMISSIONES_REQUEST_CODE);
                }
            }
        });

        ImageView db_1 = (ImageView) findViewById(R.id.bb_1) ;
        db_1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        HomeActivity.class); // 다음 넘어갈 클래스 지정
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        ImageView db_2 = (ImageView) findViewById(R.id.bb_2) ;
        db_2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        SpecialtiesActivity.class); // 다음 넘어갈 클래스 지정
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        ImageView db_3 = (ImageView) findViewById(R.id.bb_3) ;
        db_3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        TMapViewView.class); // 다음 넘어갈 클래스 지정
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // 다음 화면으로 넘어간다


            }
        });
        ImageView db_4 = (ImageView) findViewById(R.id.bb_4) ;
        db_4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        MyView.class); // 다음 넘어갈 클래스 지정
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });
    }

}
