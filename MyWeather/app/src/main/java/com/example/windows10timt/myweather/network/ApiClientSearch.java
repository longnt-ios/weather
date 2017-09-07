package com.example.windows10timt.myweather.network;

import com.example.windows10timt.myweather.listener.ApiSearchService;
import com.example.windows10timt.myweather.util.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Tien Long on 9/6/2017.
 */

public class ApiClientSearch {
    private static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constant.API_SEARCH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiSearchService searchService() {
        return retrofit().create(ApiSearchService.class);
    }
}
