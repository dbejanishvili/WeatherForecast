package com.example.weatherforcast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountryInfoAPI {
    @GET("v1/forecast.json")
    Call<CountryInfo> getCountries(@Query("key") String key, @Query("q") String country, @Query("days") String numDays);
}
