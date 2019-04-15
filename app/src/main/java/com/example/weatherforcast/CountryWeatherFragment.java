package com.example.weatherforcast;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountryWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountryWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryWeatherFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PARAM_COUNTRY_NAME = "country_name";
    private static final int DEFAULT_DAY_COUNT = 10;
    private static final String WEATHER_SERVER_KEY = "61f1b08c4f834deb834185207191204";
    private String countryName;
    private CountryInfo countryInfo;
    private int tintId = R.color.dayTint;
    private int backgrowndGradientId = R.drawable.day_gradient_background;
    private View rootView;

    public CountryWeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name name of the country.
     * @return A new instance of fragment CountryWeatherFragment.
     */
    public static CountryWeatherFragment newInstance(String name) {
        CountryWeatherFragment fragment = new CountryWeatherFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_COUNTRY_NAME, name);
        fragment.countryName = name;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryName = getArguments().getString(PARAM_COUNTRY_NAME);
        }
    }
    private void getInfoForCountry(){
        //fetch forecast for given country and update
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CountryInfoAPI api = retrofit.create(CountryInfoAPI.class);
        api.getCountryInfo(WEATHER_SERVER_KEY, countryName, DEFAULT_DAY_COUNT).enqueue(new Callback<CountryInfo>() {
            @Override
            public void onResponse(Call<CountryInfo> call, Response<CountryInfo> response) {

                if(response.isSuccessful()){
                    countryInfo = response.body();
                    if(countryInfo == null) countryInfo = new CountryInfo();

                    if(countryInfo.getTimeOfDay() == TimeOfDay.NIGHT){
                        tintId = R.color.nightTint;
                        backgrowndGradientId = R.drawable.night_gradient_background;
                    }else{
                        tintId =R.color.dayTint;
                        backgrowndGradientId = R.drawable.day_gradient_background;
                    }
                    setUpLayout();
                }
            }

            @Override
            public void onFailure(Call<CountryInfo> call, Throwable t) {
                Log.d("response_TAG","onFailure");
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView= inflater.inflate(R.layout.fragment_country_weather, container, false);
        ((TextView)rootView.findViewById(R.id.countryName)).setText(countryName);
        rootView.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        getInfoForCountry();

        return rootView;
    }

    private void setUpLayout(){

        ColorStateList tint =ContextCompat.getColorStateList(rootView.getContext(),tintId);

        ((TextView)rootView.findViewById(R.id.countryName)).setText(countryName);
        ((TextView)rootView.findViewById(R.id.timeAndDate)).setText(countryInfo.getLocalTime());
        ((TextView)rootView.findViewById(R.id.temperature)).setText(countryInfo.getCurrentTemp());
        ((TextView)rootView.findViewById(R.id.perceivedTemp)).setText(countryInfo.getPerceivedTemp());
        ((TextView)rootView.findViewById(R.id.precipitationValue)).setText(countryInfo.getCurrentPrecip());
        ((TextView)rootView.findViewById(R.id.humidityValue)).setText(countryInfo.getCurrentHumidity());
        ((TextView)rootView.findViewById(R.id.windspeedValue)).setText(countryInfo.getCurrentWindSpeed());
        ((TextView)rootView.findViewById(R.id.day_night_value)).setText(countryInfo.getDayAndNight());
        ((ImageView)rootView.findViewById(R.id.precipitationIcon)).setImageTintList(tint);
        ((ImageView)rootView.findViewById(R.id.humidityIcon)).setImageTintList(tint);
        ((ImageView)rootView.findViewById(R.id.windspeedIcon)).setImageTintList(tint);
        rootView.findViewById(R.id.mainContentLayout).setBackgroundResource(backgrowndGradientId);

        Glide.with(rootView.getContext()).load(countryInfo.getCurrentWeatherIcon())
                .into((ImageView)rootView.findViewById(R.id.sun_moon_image));


        RecyclerView weatherByDateView = rootView.findViewById(R.id.weatherByDate);
        weatherByDateView.setHasFixedSize(true);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(countryInfo.getWeatherList());
        weatherByDateView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        weatherByDateView.setAdapter(recyclerAdapter);
        rootView.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }



    @Override
    public void onDetach() {
        super.onDetach();

    }


}
