package com.example.weatherforcast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<WeatherByDateViewHolder> {

    private List<WeatherByDate> data;
    public RecyclerViewAdapter(List<WeatherByDate> data){
        this.data = data;
    }

    @NonNull
    @Override
    public WeatherByDateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_by_day_layout, viewGroup, false);

        return new WeatherByDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherByDateViewHolder itemViewHolder, int i) {

        WeatherByDate day = data.get(i);
        Glide.with(itemViewHolder.itemView.getContext()).load(day.getIconAddress()).into(itemViewHolder.icon);
        itemViewHolder.temperature.setText(day.getTemperature());
        itemViewHolder.humidity.setText(day.getHumidity());
        itemViewHolder.date.setText(day.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
