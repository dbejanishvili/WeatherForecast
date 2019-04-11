package com.example.weatherforcast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class weatherPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> countryNames;
    public weatherPagerAdapter(FragmentManager fm, ArrayList<String> countryNames) {
        super(fm);
        this.countryNames = countryNames;
    }

    @Override
    public Fragment getItem(int i) {
        return CountryWeatherFragment.newInstance(countryNames.get(i));
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }
}
