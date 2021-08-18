package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hamseong.hohaeng.R;

public class GjSpecialties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gj_specialties);


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
        View gj_image1 = (View) findViewById(R.id.gj_image1) ;
        gj_image1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "무등산수박";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image2 = (View) findViewById(R.id.gj_image2) ;
        gj_image2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "상추튀김";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image3 = (View) findViewById(R.id.gj_image3) ;
        gj_image3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "유동 오리탕";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image4 = (View) findViewById(R.id.gj_image4) ;
        gj_image4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "송정 떡갈비";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image5 = (View) findViewById(R.id.gj_image5) ;
        gj_image5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "광주 비엔날레";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image6 = (View) findViewById(R.id.gj_image6) ;
        gj_image6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "광주 세계김치축제";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image7 = (View) findViewById(R.id.gj_image7) ;
        gj_image7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "추억의 충장축제";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);

            }
        });
        View gj_image8 = (View) findViewById(R.id.gj_image8) ;
        gj_image8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srchString = "광주 청년축제";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?ie=UTF-8&query="+srchString +"&sm=chr_hty"));

                startActivity(browserIntent);


            }
        });

    }
}