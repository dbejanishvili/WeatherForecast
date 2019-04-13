package com.example.weatherforcast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class weatherByDateViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView date;
    public TextView humidity;
    public TextView temperature;

    public weatherByDateViewHolder(@NonNull View itemView) {
        super(itemView);
        this.icon = itemView.findViewById(R.id.recyclerIcon);
        this.date = itemView.findViewById(R.id.recyclerDate);
        this.humidity = itemView.findViewById(R.id.recyclerHumidity);
        this.temperature = itemView.findViewById(R.id.recyclerTemperature);
    }
}
