package com.example.todoprojectv2.model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenWeatherAPI {

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String id, @Query("units") String metric);
}
