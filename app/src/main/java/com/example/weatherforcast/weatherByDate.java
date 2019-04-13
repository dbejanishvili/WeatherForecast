package com.example.weatherforcast;

public class weatherByDate {
    private String date;
    private String iconAddress;
    private String temperature;
    private String humidity;


    public weatherByDate(String date,String iconAddress,String temperature,double humidity){
        this.date = date;
        this.iconAddress = iconAddress;
        this.temperature = temperature;
        this.humidity = Double.toString(humidity) + "%";
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDate() {
        return date;
    }

    public String getIconAddress() {
        return iconAddress;
    }

    public String getTemperature() {
        return temperature;
    }
}
