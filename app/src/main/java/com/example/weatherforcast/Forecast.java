package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("forecastday")
    private List<WeatherByDate> weather;

    public List<WeatherByDate> getWeatherList(){
        return weather;
    }

}
