package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

public class WeatherByDate {
    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private DayInfo dayInfo;

    @SerializedName("astro")
    private DateAstro astro;

    public WeatherByDate(){}

    public String getHumidity() {
        return Double.toString(dayInfo.humidity)+"%";
    }

    public String getDate() {
        return date;
    }

    public String getIconAddress() {
        return dayInfo.condition.iconAddress;
    }

    public String getTemperature() {
        return Double.toString(dayInfo.avgTemp) + " &#8451";
    }

    public String getSunrise(){
        return astro.sunrise;
    }
    public String getSunset(){
        return astro.sunset;
    }
    public String getMoonrise(){
        return astro.moonrise;
    }
    public String getMoonset(){
        return astro.moonset;
    }
}
