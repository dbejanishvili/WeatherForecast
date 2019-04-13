package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CountryInfo implements Serializable{
    @SerializedName("location")
    private CountryLocation loc;

    @SerializedName("current")
    private CountryCurrentWeather curWeather;

    @SerializedName("forecast")
    private Forecast forecast;

    public String getCountryName(){
        return loc.getCountryName();
    }

    public String getLocalTime(){
        return loc.getLocalTime();
    }

    public String getCurrentTemp(){
        return curWeather.getTemperature();
    }

    public String getCurrentHumidity(){
        return curWeather.getHumidity();
    }

    public String getCurrentPrecip(){
        return curWeather.getPrecip();
    }

    public String getCurrentWindSpeed(){
        return curWeather.getWindSpeed();
    }

    public String getCurrentWeatherIcon(){
        return curWeather.getIconAddress();
    }

    public String getPerceivedTemp(){
        return curWeather.getPerceivedTemp();
    }

    public String getDayAndNight(){
        if(forecast.getWeatherList().size()>0){
            WeatherByDate w = forecast.getWeatherList().get(0);
            return w.getSunrise() + " " + w.getSunset();
        }
        return "6:00 AM 6:00 PM";
    }

    public List<WeatherByDate> getWeatherList(){
        return forecast.getWeatherList();
    }

    public TimeOfDay getTimeOfDay(){
        //TODO add time parser

        return TimeOfDay.DAY;
    }

}
