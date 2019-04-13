package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryLocation implements Serializable {
    @SerializedName("country")
    private String countryName;

    @SerializedName("localtime")
    private String localTime;

    public String getCountryName(){
        return countryName;
    }

    public String getLocalTime(){
        return localTime;
    }

}
