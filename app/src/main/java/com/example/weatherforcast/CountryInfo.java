package com.example.weatherforcast;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryInfo implements Serializable{
    private static final String ERROR_ICON_ADDRESS = "https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png";
    @SerializedName("location")
    private CountryLocation loc;

    @SerializedName("current")
    private CountryCurrentWeather curWeather;

    @SerializedName("forecast")
    private Forecast forecast;

    public String getCountryName(){
        if(loc==null)
            return "N/A";
        return loc.getCountryName();
    }

    public String getLocalTime(){
        if(loc==null)
            return "N/A";

        String dateTime = loc.getLocalTime();
        String[] str = dateTime.split(" ");
        String[] timeStr = str[1].split(":");
        String ampm = "am";
        int hour = Integer.parseInt(timeStr[0]);
        if(hour>=12){
            hour-=12;
            ampm = "pm";
        }

        return str[0] + " " + Integer.toString(hour) + ":" + timeStr[1] + " " + ampm;
    }

    public String getCurrentTemp(){
        if(curWeather==null)
            return "N/A";
        return curWeather.getTemperature();
    }

    public String getCurrentHumidity()
    {
        if(curWeather==null)
            return "N/A";
        return curWeather.getHumidity();
    }

    public String getCurrentPrecip(){
        if(curWeather==null)
            return "N/A";
        return curWeather.getPrecip();
    }

    public String getCurrentWindSpeed(){

        if(curWeather==null)
            return "N/A";
        return curWeather.getWindSpeed();
    }

    public String getCurrentWeatherIcon(){
        if(curWeather==null)
            return ERROR_ICON_ADDRESS;
        return "http://" + curWeather.getIconAddress();
    }

    public String getPerceivedTemp(){
        if(curWeather==null)
            return "N/A";
        return curWeather.getPerceivedTemp();
    }

    public String getDayAndNight(){
        if(forecast==null)
            return "N/A";
        if(forecast.getWeatherList()==null)
            return "N/A";
        if(forecast.getWeatherList().size()>0){
            WeatherByDate w = forecast.getWeatherList().get(0);
            return w.getSunrise() + "-" + w.getSunset();
        }
        return "6:00 AM-6:00 PM";
    }

    public List<WeatherByDate> getWeatherList(){

        if(forecast==null)
            return new ArrayList<>();
        return forecast.getWeatherList();
    }

    public TimeOfDay getTimeOfDay(){
        if(loc==null)
            return TimeOfDay.DAY;

        String dateTime = loc.getLocalTime();
        String[] str = dateTime.split(" ");
        String[] timeStr = str[1].split(":");
        int hour = Integer.parseInt(timeStr[0]);
        if(hour>= 6 && hour<18)
            return TimeOfDay.DAY;

        return TimeOfDay.NIGHT;
    }

}
