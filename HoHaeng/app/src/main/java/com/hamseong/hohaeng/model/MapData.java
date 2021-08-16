package com.hamseong.hohaeng.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MapData {
    @SerializedName("documents")
    public List<Placeinfo> documents;

    public List<Placeinfo> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Placeinfo> documents) {
        this.documents = documents;
    }
}
