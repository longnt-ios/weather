package com.example.windows10timt.myweather.listener;

import com.example.windows10timt.myweather.model.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Windows 10 TIMT on 12/15/2016.
 */

public interface GithubClient {

    @GET("forecast/daily")
    Call<Example> getUser(@Query("lat") double lat , @Query("lon") double lon , @Query("cnt") int cnt , @Query("appid") String appid);


}
