package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.RecommendInfo;
import com.hamseong.hohaeng.databinding.ActivityCalBinding;

import java.util.Calendar;

public class Cal extends AppCompatActivity {
    ActivityCalBinding binding;
    MutableLiveData<Integer> childnum = new MutableLiveData<>();
    MutableLiveData<Integer> peoplenum = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cal);
        binding.setActi(this);
        childnum.setValue(0);
        peoplenum.setValue(0);
        Intent info = getIntent();

        childnum.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.childCount.setText(Integer.toString(integer));
            }
        });


        peoplenum.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.peopleCount.setText(Integer.toString(integer));
            }
        });


        binding.imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapViewView.class);
                intent.putExtra("smallpeople",childnum.getValue());
                intent.putExtra("people",peoplenum.getValue());
                intent.putExtra("urban", info.getExtras().getString("urban"));
                Calendar calendar =  binding.calendarView.getSelectedDates().get(0);
                intent.putExtra("start",Integer.toString(calendar.YEAR)+"."+Integer.toString(calendar.MONTH+1)+"."+Integer.toString(calendar.DAY_OF_MONTH));
                calendar =  binding.calendarView.getSelectedDates().get(binding.calendarView.getSelectedDates().size()-1);
                intent.putExtra("end",Integer.toString(calendar.MONTH+1)+"."+Integer.toString(calendar.DAY_OF_MONTH));
                intent.putExtra("people",childnum.getValue()+peoplenum.getValue());
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
    public void minusClick(View view) {
        switch (Integer.parseInt((String)view.getTag())){
            case 8:
                childnum.setValue(childnum.getValue()-1);
                break;
            case 9:
                peoplenum.setValue(peoplenum.getValue()-1);
                break;

        }

    }
    public void plusClick(View view){
        switch (Integer.parseInt((String)view.getTag())){
            case 8:
                childnum.setValue(childnum.getValue()+1);
                break;
            case 9:
                peoplenum.setValue(peoplenum.getValue()+1);
                break;

        }
    }

}