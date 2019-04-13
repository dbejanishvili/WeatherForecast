package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("Forecast")
    private List<WeatherByDate> weather = null;

    public List<WeatherByDate> getWeatherList(){
        return weather;
    }

}
