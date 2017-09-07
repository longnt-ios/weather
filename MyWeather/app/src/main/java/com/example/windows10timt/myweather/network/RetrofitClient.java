package com.example.windows10timt.myweather.network;

import com.example.windows10timt.myweather.listener.GithubClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows 10 TIMT on 12/16/2016.
         */

public class RetrofitClient {
    private static final String url = "http://api.openweathermap.org/data/2.5/";

    private static Retrofit getRetrofitIntance(){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static GithubClient getGithubService(){
        return getRetrofitIntance().create(GithubClient.class);
    }
}
