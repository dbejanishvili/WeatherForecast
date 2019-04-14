package com.example.weatherforcast;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private WeatherPagerAdapter mWeatherPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Retrofit retrofit;
    private CountryNameAPI api;
    private ArrayList<String> countryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryNames = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CountryNameAPI.class);


        api.getCountries("name").enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if(response.isSuccessful()){
                    for (Country country : response.body()){
                        countryNames.add(country.getName());
                    }

                    // Create the adapter that will return a fragment for each of the three
                    // primary sections of the activity.
                    mWeatherPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(),countryNames);

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.viewPager);
                    mViewPager.setAdapter(mWeatherPagerAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("response_TAG","onFailure");
            }
        });





    }






}
