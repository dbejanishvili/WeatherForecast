package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

public class CountryCurrentWeather {
    @SerializedName("temp_c")
    private Double temperature;

    @SerializedName("wind_kph")
    private double windSpeed;

    @SerializedName("precip_in")
    private double precip;

    @SerializedName("feelslike_c")
    private double perceivedTemp;

    @SerializedName("condition")
    private WeatherCondition condition;

    @SerializedName("humidity")
    private double humidity;


    public String getTemperature() {
        return Double.toString(temperature) + " \u2103";
    }

    public String  getWindSpeed() {
        return Double.toString(windSpeed) + " kph";
    }

    public String getPrecip() {
        double x = precip*100;
        return Double.toString(x)  + "%";
    }

    public String getIconAddress(){
        return condition.iconAddress;
    }

    public String getHumidity(){
        return Double.toString(humidity) + "%";
    }

    public String getPerceivedTemp(){
        return Double.toString(perceivedTemp) + " \u2103";
    }
}
