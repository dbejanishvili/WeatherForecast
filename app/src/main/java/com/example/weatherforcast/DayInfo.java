package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

public class DayInfo {
    @SerializedName("condition")
    public WeatherCondition condition;

    @SerializedName("avgtemp_c")
    public double avgTemp;

    @SerializedName("avghumidity")
    public double humidity;

}
