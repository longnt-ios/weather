package com.example.windows10timt.myweather.listener;

import com.example.windows10timt.myweather.model.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows 10 TIMT on 12/16/2016.
 */

public interface UserService {
    @GET("public/yql")
    Call<Data> getTask(@Query("q") String q ,@Query("format") String format);
}
