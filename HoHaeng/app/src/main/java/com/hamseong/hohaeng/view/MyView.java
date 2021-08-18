package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityMyViewBinding;
import com.hamseong.hohaeng.model.AllCourseInfo;
import com.hamseong.hohaeng.model.AllPlaceInfo;
import com.hamseong.hohaeng.viewmodel.MyViewModel;
import com.skt.Tmap.TmapAuthentication;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class MyView extends AppCompatActivity {
    ActivityMyViewBinding binding;
    MyViewModel myViewModel = new MyViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_view);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//전체화면
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myViewModel.Courseinfo.observe(this, new Observer<ArrayList<AllCourseInfo>>() {
            int dpbase = (int) getResources().getDimension(R.dimen.base);
            int Count =0;

            @Override

            public void onChanged(ArrayList<AllCourseInfo> allCourseInfos) {
                for(AllCourseInfo allCourseInfo : allCourseInfos) {
                    LinearLayout base = new LinearLayout(MyView.this);
                    base.setBackgroundResource(R.drawable.myview_box);
                    base.setPadding(dpbase*10,dpbase*5,dpbase*10,dpbase*5);

                    LinearLayout texts = new LinearLayout(MyView.this);
                    texts.setOrientation(LinearLayout.VERTICAL);
                    texts.setPadding(dpbase*10,0,0,0);

                    TextView urban = new TextView(MyView.this);
                    urban.setTextSize(dpbase * 5);
                    urban.setTextColor(Color.BLACK);
                    urban.setGravity(Gravity.CENTER_VERTICAL);
                    urban.setText(allCourseInfo.getLocation());

                    TextView day = new TextView(MyView.this);
                    day.setText(allCourseInfo.getStartYear()+"."+allCourseInfo.getStartMonth()+"."+allCourseInfo.getStartDay()+"~"+
                            allCourseInfo.getEndYear()+"."+allCourseInfo.getEndMonth()+"."+allCourseInfo.getStartDay());
                    day.setTextColor(Color.BLUE);
                    day.setTextSize(dpbase*4);
                    day.setGravity(Gravity.CENTER_VERTICAL);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpbase*200,dpbase*50);
                    texts.addView(urban,params);
                    params = new LinearLayout.LayoutParams(dpbase*200,dpbase*30);
                    texts.addView(day,params);
                    params = new LinearLayout.LayoutParams(dpbase*200, LinearLayout.LayoutParams.MATCH_PARENT);
                    base.addView(texts,params);
                    ImageView sel = new ImageView(MyView.this);
                    sel.setPadding(0,dpbase*30,0,0);
                    sel.setImageResource(R.drawable.myview_button_sel);
                    sel.setTag(Count);
                    base.addView(sel);
                    Count++;
                    binding.scrollNow.addView(base);

                    sel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //sqlite 등록
                        }
                    });

                }
            }
        });

        myViewModel.oldCourseinfo.observe(this, new Observer<ArrayList<AllCourseInfo>>() {
            int dpbase = (int) getResources().getDimension(R.dimen.base);
            int Count =0;
            @Override
            public void onChanged(ArrayList<AllCourseInfo> allCourseInfos) {
                for(AllCourseInfo allCourseInfo : allCourseInfos) {
                    LinearLayout base = new LinearLayout(MyView.this);
                    base.setBackgroundResource(R.drawable.myview_box);
                    base.setPadding(dpbase * 10, dpbase * 5, dpbase * 10, dpbase * 5);

                    LinearLayout texts = new LinearLayout(MyView.this);
                    texts.setOrientation(LinearLayout.VERTICAL);
                    texts.setPadding(dpbase * 10, 0, 0, 0);

                    TextView urban = new TextView(MyView.this);
                    urban.setTextSize(dpbase * 5);
                    urban.setTextColor(Color.BLACK);
                    urban.setGravity(Gravity.CENTER_VERTICAL);
                    urban.setText(allCourseInfo.getLocation());

                    TextView day = new TextView(MyView.this);
                    day.setText(allCourseInfo.getStartYear() + "." + allCourseInfo.getStartMonth() + "." + allCourseInfo.getStartDay() + "~" +
                            allCourseInfo.getEndYear() + "." + allCourseInfo.getEndMonth() + "." + allCourseInfo.getStartDay());
                    day.setTextColor(Color.BLUE);
                    day.setTextSize(dpbase * 4);
                    day.setGravity(Gravity.CENTER_VERTICAL);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpbase * 200, dpbase * 50);
                    texts.addView(urban, params);
                    params = new LinearLayout.LayoutParams(dpbase * 200, dpbase * 30);
                    texts.addView(day, params);
                    params = new LinearLayout.LayoutParams(dpbase * 200, LinearLayout.LayoutParams.MATCH_PARENT);
                    base.addView(texts, params);
                    ImageView sel = new ImageView(MyView.this);
                    sel.setPadding(0, dpbase * 30, 0, 0);
                    sel.setImageResource(R.drawable.myview_button_sel);
                    sel.setTag(Count);
                    base.addView(sel);
                    Count++;
                    binding.scrollOld.addView(base);

                    sel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MyView.this,CourseRegister.class);
                            intent.putExtra("course",allCourseInfos.get((int)v.getTag()));
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        binding.myviewJNotsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myviewJNotsel.setVisibility(View.GONE);
                binding.myviewJSel.setVisibility(View.VISIBLE);
                binding.myviewYSel.setVisibility(View.GONE);
                binding.myviewYNotsel.setVisibility(View.VISIBLE);
            }
        });

        binding.myviewYNotsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myviewJNotsel.setVisibility(View.VISIBLE);
                binding.myviewJSel.setVisibility(View.GONE);
                binding.myviewYSel.setVisibility(View.VISIBLE);
                binding.myviewYNotsel.setVisibility(View.GONE);
            }
        });

        //네비게이션 바 클릭 리스너
        {
            View db_1 = (View) findViewById(R.id.bb_1) ;
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
            View db_2 = (View) findViewById(R.id.bb_2) ;
            db_2.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        LikeView.class); // 다음 넘어갈 클래스 지정
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // 다음 화면으로 넘어간다
                }
            });
            View db_3 = (View) findViewById(R.id.bb_3) ;
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
            View db_4 = (View) findViewById(R.id.bb_4) ;
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
            View myview_photo = (View) findViewById(R.id.myview_photo) ;
            myview_photo.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            LoginActivity.class); // 다음 넘어갈 클래스 지정
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent); // 다음 화면으로 넘어간다

                }
            });
        }

    }
}