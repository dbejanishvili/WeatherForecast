package com.example.weatherforcast;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

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
    private static final String DEFAULT_DAY_COUNT = "10";
    private static final String WEATHER_SERVER_KEY = "61f1b08c4f834deb834185207191204";
    private String countryName;
    private Retrofit retrofit;
    private CountryInfoAPI api;
    private CountryInfo countryInfo;
    private int tintId;
    private int backgrowndGradientId = R.drawable.day_gradient_background;
    private OnFragmentInteractionListener mListener;

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
        fragment.setArguments(args);
        fragment.getInfoForCountry();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryName = getArguments().getString(PARAM_COUNTRY_NAME);
            getInfoForCountry();
        }
    }
    private void getInfoForCountry(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CountryInfoAPI.class);

        api.getCountries(WEATHER_SERVER_KEY, countryName, DEFAULT_DAY_COUNT).enqueue(new Callback<CountryInfo>() {
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

        View rootView = inflater.inflate(R.layout.fragment_country_weather, container, false);
        setUpLayout(rootView);

        return rootView;
    }

    private void setUpLayout(View rootView){

        ColorStateList tint =ContextCompat.getColorStateList(rootView.getContext(),R.color.nightTint);

        ((TextView)rootView.findViewById(R.id.countryName)).setText(countryInfo.getCountryName());
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
        Adapter recyclerAdapter = new Adapter(countryInfo.getWeatherList());
        weatherByDateView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        weatherByDateView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
