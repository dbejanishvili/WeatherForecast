package com.example.weatherforcast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> countryNames;
    public WeatherPagerAdapter(FragmentManager fm, ArrayList<String> countryNames) {
        super(fm);
        this.countryNames = countryNames;

    }

    @Override
    public Fragment getItem(int i) {
        String name = countryNames.get(i);
        return CountryWeatherFragment.newInstance(name);
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }
}
