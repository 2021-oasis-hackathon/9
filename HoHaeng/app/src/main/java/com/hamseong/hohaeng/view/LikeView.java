package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityLikeViewBinding;
import com.hamseong.hohaeng.model.AllCourseInfo;
import com.hamseong.hohaeng.model.AllPlaceInfo;
import com.hamseong.hohaeng.model.Placeinfo;
import com.hamseong.hohaeng.viewmodel.LikeViewModel;

import java.util.ArrayList;

public class LikeView extends AppCompatActivity {
    LikeViewModel likeViewModel = new LikeViewModel();
    ActivityLikeViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_like_view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//전체화면
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        likeViewModel.likeInfolist.observe(this, new Observer<ArrayList<AllPlaceInfo>>() {
            int dpbase = (int)getResources().getDimension(R.dimen.base);
            int Count;
            @Override
            public void onChanged(ArrayList<AllPlaceInfo> placeinfos) {

                for(AllPlaceInfo allPlaceInfo : placeinfos){


                    LinearLayout base = new LinearLayout(LikeView.this);
                    base.setBackgroundResource(R.drawable.likeview_box);
                    base.setOrientation(LinearLayout.VERTICAL);
                    base.setPadding(dpbase * 10, dpbase * 5, dpbase * 10, dpbase * 5);

                    LinearLayout texts = new LinearLayout(LikeView.this);
                    texts.setOrientation(LinearLayout.HORIZONTAL);
                    texts.setPadding(dpbase * 20, 0, 0, 0);

                    TextView Name = new TextView(LikeView.this);
                    Name.setTextSize(dpbase * 7);
                    Name.setTextColor(Color.BLACK);
                    Name.setGravity(Gravity.CENTER_VERTICAL);
                    Name.setText(allPlaceInfo.getName());

                    ImageView like = new ImageView(LikeView.this);
                    like.setImageResource(R.drawable.like);
                    LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(dpbase*180, LinearLayout.LayoutParams.MATCH_PARENT);
                    texts.addView(Name,para);
                    texts.addView(like);

                    TextView star = new TextView(LikeView.this);
                    star.setText("★ "+allPlaceInfo.getStar());
                    star.setTextColor(Color.BLACK);
                    star.setTextSize(dpbase * 4);
                    star.setPadding(dpbase*20,0,0,0);
                    star.setGravity(Gravity.CENTER_VERTICAL);

                    ImageView line = new ImageView(LikeView.this);
                    line.setImageResource(R.drawable.myview_line);
                    line.setPadding(20*dpbase,10*dpbase,20*dpbase,10*dpbase);
                    base.addView(texts);
                    base.addView(star);
                    base.addView(line);
                    Count++;
                    binding.slidingLike.addView(base);

                }
            }
        });
        ArrayList<AllPlaceInfo> allCourseInfos = new ArrayList<>();
        allCourseInfos.add(new AllPlaceInfo("카페","카페","9","27","2021","4.43","13,243","도로명","010",null));
        likeViewModel.likeInfolist.setValue(allCourseInfos);

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
    }
}