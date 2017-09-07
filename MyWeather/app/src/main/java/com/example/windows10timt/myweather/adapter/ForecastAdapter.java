package com.example.windows10timt.myweather.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.model.model.ProductForecast;

import java.util.ArrayList;

/**
 * Created by Windows 10 TIMT on 12/21/2016.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<ProductForecast> items;

    public ForecastAdapter(Context context, ArrayList<ProductForecast> items ) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ProductForecast forecast = items.get(position);
        holder.day.setText(forecast.getDay());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        boolean check = preferences.getBoolean("check",true);

        if (check == true) {
            int high = Integer.valueOf(String.format(forecast.getHigh()));

            int low  = Integer.valueOf(String.format(forecast.getLow()));

            int newHigh = (int) Math.round((high - 32) / 1.8);
            int newLow = (int) Math.round((low - 32) / 1.8);

            String myHigh = String.valueOf(newHigh);
            String myLow = String.valueOf(newLow);


            holder.high.setText(myHigh+"°");
            holder.low.setText(myLow+"°");

        }
        if (check == false) {
            holder.high.setText(forecast.getHigh()+"°");
            holder.low.setText(forecast.getLow()+"°");
        }


        if (forecast.getText().equals("Showers") || forecast.getText().equals("Scattered showers")) {
            holder.description.setImageResource(R.drawable.snow);
        } else if (forecast.getText().equals("Breezy")) {
            holder.description.setImageResource(R.drawable.breezy);
        } else if (forecast.getText().equals("Sunny") || forecast.getText().equals("Mostly Sunny")) {
            holder.description.setImageResource(R.drawable.sunny);
        } else if (forecast.getText().equals("Partly Cloudy") || forecast.getText().equals("Cloudy") || forecast.getText().equals("Mostly Cloudy")) {
            holder.description.setImageResource(R.drawable.cloud_much);
        }else if (forecast.getText().equals("Rain")){
            holder.description.setImageResource(R.drawable.rain);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day, high, low;
        public ImageView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.mDay);
            high = (TextView) itemView.findViewById(R.id.mTempHigh);
            low = (TextView) itemView.findViewById(R.id.mTempLow);
            description = (ImageView) itemView.findViewById(R.id.mImage);
        }
    }
}
