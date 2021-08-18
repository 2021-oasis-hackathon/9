package com.hamseong.hohaeng.viewmodel;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.hamseong.hohaeng.model.AllPlaceInfo;
import com.hamseong.hohaeng.model.AllUser;
import com.hamseong.hohaeng.model.Placeinfo;

import java.util.ArrayList;

public class LikeViewModel {
    public MutableLiveData<ArrayList<AllPlaceInfo>> likeInfolist = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Pair<String,String>>> chatlist =new MutableLiveData<>();
    public MutableLiveData<ArrayList<AllUser>> userlist = new MutableLiveData<>()


    public void viewLoading(){

    }

}
