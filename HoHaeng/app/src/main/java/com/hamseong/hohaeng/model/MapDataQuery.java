package com.hamseong.hohaeng.model;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MapDataQuery {

    @GET("v2/local/search/keyword.json")    // Keyword.json의 정보를 받아옴
    Call<MapData> getPost(@Header("Authorization") String apikey,
                          @Query("query") String query,
                          @Query("category_group_code") String categoty,
                          @Query("x") String x,
                          @Query("y") String y,
                          @Query("radius") int radius,
                          @Query("size") int size
                          );

    @GET("v2/local/search/keyword.json")    // Keyword.json의 정보를 받아옴
    Call<MapData> getInfo(@Header("Authorization") String apikey,
                          @Query("query") String query,
                          @Query("x") String x,
                          @Query("y") String y,
                          @Query("size") int size
    );

}


