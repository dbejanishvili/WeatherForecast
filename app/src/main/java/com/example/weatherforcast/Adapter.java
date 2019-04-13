package com.example.weatherforcast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<weatherByDateViewHolder> {

    private List<weatherByDate> data;
    public Adapter(List<weatherByDate> data){
        this.data = data;
    }

    @NonNull
    @Override
    public weatherByDateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_by_day_layout, viewGroup, false);

        return new weatherByDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull weatherByDateViewHolder itemViewHolder, int i) {

        weatherByDate day = data.get(i);
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
