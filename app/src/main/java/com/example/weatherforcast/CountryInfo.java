package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryInfo implements Serializable {
    public String country;
    public String localtime;

    @SerializedName("temp_c")
    public double temperature;
    public String currentSunrise;
    public String currentSunset;
    public String icon;
    public double wind_kph;
    public double humidity;

    @SerializedName("precip_in")
    public double precip;

    @SerializedName("feelslike_c")
    public double preceivedTemp;

    public class infoBydate{
        public String date;

        public String sunrise;
        public String sunset;
    }




}
