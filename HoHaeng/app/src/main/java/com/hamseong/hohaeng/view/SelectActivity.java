package com.hamseong.hohaeng.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hamseong.hohaeng.R;

public class SelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ImageView back = (ImageView) findViewById(R.id.d_back) ;
        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ImageView mywork =  findViewById(R.id.selectview_mywork);
        mywork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = getIntent();
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Cal.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("urban", info.getExtras().getString("urban"));
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ImageView recommend = findViewById(R.id.selectview_recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = getIntent();
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        RecommendSel.class); // 다음 넘어갈 클래스 지정
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

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
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        SpecialtiesActivity.class); // 다음 넘어갈 클래스 지정
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent); // 다음 화면으로 넘어간다
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