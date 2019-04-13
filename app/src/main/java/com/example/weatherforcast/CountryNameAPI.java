package com.example.weatherforcast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountryNameAPI {

    @GET("rest/v2/all")
    Call<List<Country>> getCountries(@Query("fields") String field);
}
