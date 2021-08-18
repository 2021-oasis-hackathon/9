package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                intent.putExtra("start",Integer.toString(calendar.MONTH+1)+"월"+Integer.toString(calendar.DAY_OF_MONTH)+"일");
                calendar =  binding.calendarView.getSelectedDates().get(binding.calendarView.getSelectedDates().size()-1);
                intent.putExtra("end",Integer.toString(calendar.MONTH+1)+"월"+Integer.toString(calendar.DAY_OF_MONTH)+"일");
                startActivity(intent);
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