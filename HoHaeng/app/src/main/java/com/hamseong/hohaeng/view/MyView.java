package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityMyViewBinding;

import net.daum.mf.map.api.MapView;

public class MyView extends AppCompatActivity {
    ActivityMyViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_view);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//전체화면
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
}