package com.hamseong.hohaeng.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hamseong.hohaeng.R;
import com.hamseong.hohaeng.databinding.ActivityCourseRegisterBinding;
import com.hamseong.hohaeng.model.AllCourseInfo;
import com.hamseong.hohaeng.model.AllPlaceInfo;
import com.hamseong.hohaeng.viewmodel.CourseRegisterViewModel;

import java.util.ArrayList;

public class CourseRegister extends AppCompatActivity {
    CourseRegisterViewModel courseRegisterViewModel = new CourseRegisterViewModel();
    ActivityCourseRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_course_register);

        Intent info = getIntent();

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllCourseInfo allCourseInfo = (AllCourseInfo) info.getSerializableExtra("course");
                courseRegisterViewModel.onRegisterButtonClick((String)binding.editTextTextTitle.getText().toString(),binding.editTextTextStar.getText().toString(),binding.editTextTextContent.getText().toString(),allCourseInfo);
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
}