package com.example.weatherforcast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> countryNames;
    //private Map<String, CountryInfo> fullData;
    public WeatherPagerAdapter(FragmentManager fm, ArrayList<String> countryNames) {
        super(fm);
        this.countryNames = countryNames;
        //fullData = new HashMap<>();
       // getFullData();
    }

   /* private void getFullData(){
        for(int i=0;i<countryNames.size();i++){
            String country = countryNames.get(i);
            CountryInfo info =  new CountryInfo();
           // fullData.put(country,info);
        }
    }*/

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
