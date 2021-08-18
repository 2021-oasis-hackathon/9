package com.hamseong.hohaeng.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class tc {
    @TypeConverter
    public static ArrayList<AllCourseInfo> fromString(String value) {
        Type listType = new TypeToken<ArrayList<AllCourseInfo>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<AllCourseInfo> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
