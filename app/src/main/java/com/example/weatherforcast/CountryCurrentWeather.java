package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

public class CountryCurrentWeather {
    private static final String ERROR_ICON_ADDRESS = "https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png";

    @SerializedName("temp_c")
    private Double temperature;

    @SerializedName("wind_kph")
    private Double windSpeed;

    @SerializedName("precip_in")
    private Double precip;

    @SerializedName("feelslike_c")
    private Double perceivedTemp;

    @SerializedName("condition")
    private WeatherCondition condition;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("is_day")
    private Integer isday;



    public String getTemperature() {
        if(temperature == null)
            return "N/A";
        return Double.toString(temperature) + " \u2103";
    }

    public String  getWindSpeed() {
        if(windSpeed == null)
            return "N/A";
        return Double.toString(windSpeed) + " kph";
    }

    public String getPrecip() {
        if(precip == null)
            return "N/A";
        double x = precip*100;
        return Double.toString(x)  + "%";
    }

    public String getIconAddress(){
        if(condition == null)
            return ERROR_ICON_ADDRESS;
        return condition.iconAddress;
    }

    public String getHumidity(){
        if(humidity == null)
            return "N/A";
        return Double.toString(humidity) + "%";
    }

    public String getPerceivedTemp(){

        if(windSpeed == null)
            return "N/A";
        return Double.toString(perceivedTemp) + "\u2103";
    }

    public TimeOfDay getTimeOfDay() {
        if(isday == null || isday==0)
            return TimeOfDay.NIGHT;
        return TimeOfDay.DAY;
    }
}
