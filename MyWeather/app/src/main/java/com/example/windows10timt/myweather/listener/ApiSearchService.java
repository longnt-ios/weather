package com.example.windows10timt.myweather.listener;

import com.example.windows10timt.myweather.model.model.SearchRespone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by Nguyen Tien Long on 9/6/2017.
 */

public interface ApiSearchService {

    @GET("https://www.yahoo.com/news/_td/api/resource/WeatherSearch;text={place}")
    Call<List<SearchRespone>> getSearchQuery(@Path("place") String query);
}
