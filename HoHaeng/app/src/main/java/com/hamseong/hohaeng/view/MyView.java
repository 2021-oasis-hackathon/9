package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

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

            findViewById(R.id.navi_map).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("콘텍스트 겹침", "초기화합니다");
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

            findViewById(R.id.navi_like).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                    Intent intent = new Intent(getApplicationContext(), LikeView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });
        }

    }
}