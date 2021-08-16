package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hamseong.hohaeng.APIKey;
import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityTmapViewBinding;
import com.hamseong.hohaeng.model.TMapViewPointInfo;
import com.hamseong.hohaeng.viewmodel.TMapViewModel;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class TMapViewView extends AppCompatActivity {

    TMapView tMapView;
    TMapViewModel mTMapViewModel = new TMapViewModel();
    ActivityTmapViewBinding binding;

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

        mTMapViewModel.viewCreate(tMapView);

        mTMapViewModel.mTMapPoints.observe(this, new Observer<ArrayList<TMapViewPointInfo>>() {

            int Count= 1;

            @Override
            public void onChanged(ArrayList<TMapViewPointInfo> pointInfos) {
                binding.slideList.removeAllViewsInLayout();
                Count=1;
                tMapView.removeAllTMapPolyLine();
                for(TMapViewPointInfo info : pointInfos){
                    if(Count!=pointInfos.size()){
                        TMapPolyLine tMapPolyLine = mTMapViewModel.getroad(new Pair<TMapPoint, TMapPoint>(pointInfos.get(Count-1).gettMapPoint(),pointInfos.get(Count).gettMapPoint()));
                        tMapView.addTMapPolyLine(Integer.toString(Count),tMapPolyLine);
                    }
                    TMapMarkerItem markerItem1 = new TMapMarkerItem();
                    Bitmap bitmap = BitmapFactory.decodeResource(TMapViewView.this.getResources(), R.drawable.marker_sel);

                    markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                    markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                    markerItem1.setTMapPoint( info.gettMapPoint() ); // 마커의 좌표 지정
                    markerItem1.setName(info.getName()); // 마커의 타이틀 지정
                    tMapView.addMarkerItem(Integer.toString(Count), markerItem1); // 지도에 마커 추가

                    ImageView line = new ImageView(TMapViewView.this);
                    line.setImageResource(R.drawable.myview_line);
                    binding.slideList.addView(line);
                    LinearLayout linearLayout = new LinearLayout(TMapViewView.this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    int size = (int)getResources().getDimension(R.dimen.tmapview_list);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, size);
                    linearLayout.setLayoutParams(layoutParams);
                    ImageView numbericon = new ImageView(TMapViewView.this);
                    switch (Count){
                        case 1:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_1);
                            break;
                        case 2:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_2);
                            break;
                        case 3:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_3);
                            break;
                        case 4:
                        default:
                            numbericon.setImageResource(R.drawable.tmapview_numbericon_4);
                            break;
                    }
                    linearLayout.addView(numbericon);

                    TextView textView = new TextView(TMapViewView.this);
                    textView.setText(info.getName());
                    textView.setTag(Count);
                    linearLayout.addView(textView);

                    ImageView car = new ImageView(TMapViewView.this);
                    car.setImageResource(R.drawable.tmapview_car);
                    linearLayout.addView(car);

                    LinearLayout updown = new LinearLayout(TMapViewView.this);
                    ImageView upbutton = new ImageView(TMapViewView.this);
                    upbutton.setImageResource(R.drawable.tmapview_upbutton);
                    upbutton.setTag(Count);
                    ImageView downbutton = new ImageView(TMapViewView.this);
                    downbutton.setImageResource(R.drawable.tmapview_downbutton);
                    downbutton.setTag(Count);
                    updown.addView(upbutton);
                    updown.addView(downbutton);
                    linearLayout.addView(updown);

                    ImageView delbutton = new ImageView(TMapViewView.this);
                    delbutton.setImageResource(R.drawable.tmapview_del);
                    delbutton.setTag(Count);
                    linearLayout.addView(delbutton);

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
                }
            }
        });

        findViewById(R.id.navi_map).setOnClickListener(new View.OnClickListener() {
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
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LikeView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

            }
        });

        findViewById(R.id.navi_info).setOnClickListener(new View.OnClickListener() {
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

}
