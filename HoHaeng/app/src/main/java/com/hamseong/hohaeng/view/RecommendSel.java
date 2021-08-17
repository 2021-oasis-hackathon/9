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
import com.hamseong.hohaeng.databinding.ActivityRecommendSelBinding;

public class RecommendSel extends AppCompatActivity {
    ActivityRecommendSelBinding binding;
    MutableLiveData<RecommendInfo> recommendInfoMutableLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recommend_sel);

        binding.setActi(this);

        recommendInfoMutableLiveData.observe(this, new Observer<RecommendInfo>() {
            @Override
            public void onChanged(RecommendInfo recommendInfo) {
                TextView text = findViewById(R.id.Count_1);
                text.setText(Integer.toString(recommendInfo.getRestaurant()));
                text = findViewById(R.id.Count_2);
                text.setText(Integer.toString(recommendInfo.getCafe()));
                text = findViewById(R.id.Count_3);
                text.setText(Integer.toString(recommendInfo.getPhoto()));
                text = findViewById(R.id.Count_4);
                text.setText(Integer.toString(recommendInfo.getNature()));
                text = findViewById(R.id.Count_5);
                text.setText(Integer.toString(recommendInfo.getActivity()));
                text = findViewById(R.id.Count_6);
                text.setText(Integer.toString(recommendInfo.getGallery()));
                text = findViewById(R.id.Count_7);
                text.setText(Integer.toString(recommendInfo.getLocal()));
                text = findViewById(R.id.Count_8);
                text.setText(Integer.toString(recommendInfo.getChild()));
                text = findViewById(R.id.Count_9);
                text.setText(Integer.toString(recommendInfo.getHistory()));
            }
        });

        binding.selBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AlreadyMapView.class);
                intent.putExtra("who","recommend");
                intent.putExtra("setting",recommendInfoMutableLiveData.getValue());
                startActivity(intent);
            }
        });


    }
    public void minusClick(View view) {
        RecommendInfo recommendInfo;
        if (recommendInfoMutableLiveData.getValue() != null){
           recommendInfo = recommendInfoMutableLiveData.getValue();
        } else{
            recommendInfo = new RecommendInfo();
        }
        switch (Integer.parseInt((String)view.getTag())){
            case 1:
                recommendInfo.setRestaurant(recommendInfo.getRestaurant()-1);
                break;
            case 2:
                recommendInfo.setCafe(recommendInfo.getCafe()-1);
                break;
            case 3:
                recommendInfo.setPhoto(recommendInfo.getPhoto()-1);
                break;
            case 4:
                recommendInfo.setRestaurant(recommendInfo.getNature()-1);
                break;
            case 5:
                recommendInfo.setActivity(recommendInfo.getActivity()-1);
                break;
            case 6:
                recommendInfo.setGallery(recommendInfo.getGallery()-1);
                break;
            case 7:
                recommendInfo.setLocal(recommendInfo.getLocal()-1);
                break;
            case 8:
                recommendInfo.setChild(recommendInfo.getChild()-1);
                break;
            case 9:
                recommendInfo.setHistory(recommendInfo.getHistory()-1);
                break;

        }
        recommendInfoMutableLiveData.setValue(recommendInfo);
    }
    public void plusClick(View view){
        RecommendInfo recommendInfo;
        if (recommendInfoMutableLiveData.getValue() != null){
            recommendInfo = recommendInfoMutableLiveData.getValue();
        } else{
            recommendInfo = new RecommendInfo();
        }
        switch (Integer.parseInt((String)view.getTag())){
            case 1:
                recommendInfo.setRestaurant(recommendInfo.getRestaurant()+1);
                break;
            case 2:
                recommendInfo.setCafe(recommendInfo.getCafe()+1);
                break;
            case 3:
                recommendInfo.setPhoto(recommendInfo.getPhoto()+1);
                break;
            case 4:
                recommendInfo.setRestaurant(recommendInfo.getNature()+1);
                break;
            case 5:
                recommendInfo.setActivity(recommendInfo.getActivity()+1);
                break;
            case 6:
                recommendInfo.setGallery(recommendInfo.getGallery()+1);
                break;
            case 7:
                recommendInfo.setLocal(recommendInfo.getLocal()+1);
                break;
            case 8:
                recommendInfo.setChild(recommendInfo.getChild()+1);
                break;
            case 9:
                recommendInfo.setHistory(recommendInfo.getHistory()+1);
                break;

        }
        recommendInfoMutableLiveData.setValue(recommendInfo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}