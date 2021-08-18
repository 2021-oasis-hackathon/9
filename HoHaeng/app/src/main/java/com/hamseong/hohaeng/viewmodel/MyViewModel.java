package com.hamseong.hohaeng.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.hamseong.hohaeng.model.AllCourseInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyViewModel {
    public MutableLiveData<ArrayList<AllCourseInfo>> oldCourseinfo = new MutableLiveData<>();
    public MutableLiveData<ArrayList<AllCourseInfo>> Courseinfo = new MutableLiveData<>();


    public void ViewLoading(){
        //쿼리 보내고 응답이
        ArrayList<AllCourseInfo> allCourseInfos; //이거로 옴
        ArrayList<AllCourseInfo> old = new ArrayList<>();
        ArrayList<AllCourseInfo> current = new ArrayList<>();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String year = new SimpleDateFormat("yyyy").format(date);
        String month = new SimpleDateFormat("M").format(date);
        String day = new SimpleDateFormat("d").format(date);
        /*for(AllCourseInfo allCourseInfo : allCourseInfos){
            if(allCourseInfo.getEndYear()<Integer.parseInt(year)||allCourseInfo.getEndMonth()<Integer.parseInt(month)||allCourseInfo.getEndDay()<Integer.parseInt(day)){
                old.add(allCourseInfo);
            }else{
                current.add(allCourseInfo);
            }
        }*/

        oldCourseinfo.setValue(old);
        Courseinfo.setValue(current);
    }
}
