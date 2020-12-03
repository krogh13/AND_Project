package com.example.todoprojectv2.model.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OpenWeatherAPI weatherAPI = retrofit.create(OpenWeatherAPI.class);

    public static OpenWeatherAPI getWeatherAPI() {
        return weatherAPI;
    }
}
