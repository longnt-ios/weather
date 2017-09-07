package com.example.windows10timt.myweather.network;

import com.example.windows10timt.myweather.listener.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows 10 TIMT on 12/16/2016.
 */

public class RetrofitClientWeather {
    private static final String url = "http://query.yahooapis.com/v1/";

    private static Retrofit getRetrofitWeather() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static UserService getGithubServiceWeather() {
        return getRetrofitWeather().create(UserService.class);
    }
}
