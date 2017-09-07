package com.example.windows10timt.myweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.model.model.Astronomy;
import com.example.windows10timt.myweather.model.model.Atmosphere;

/**
 * Created by Windows 10 TIMT on 12/21/2016.
 */

public class AdapterLayout5 extends RecyclerView.Adapter<AdapterLayout5.MyViewHolder> {
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    public static final int LAYOUT_1 = 0;
    public static final int LAYOUT_2 = 1;
    public static final int LAYOUT_3 = 2;
    public static final int LAYOUT_4 = 3;

    public AdapterLayout5(Atmosphere atmosphere, Astronomy astronomy) {
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LAYOUT_1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details_1, parent, false);
            return new MyViewHolder(view);
        }
        if (viewType == LAYOUT_2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details_2, parent, false);
            return new MyViewHolder(view);
        }
        if (viewType == LAYOUT_3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details_3, parent, false);
            return new MyViewHolder(view);
        }
        if (viewType == LAYOUT_4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details_4, parent, false);
            return new MyViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == LAYOUT_1){
            TextView mHumidity = (TextView) holder.itemView.findViewById(R.id.mHumidity);
            String humidity = atmosphere.getHumidity();
            mHumidity.setText(humidity+" %");
        }
        if (getItemViewType(position) == LAYOUT_2){
            TextView mVisibility = (TextView) holder.itemView.findViewById(R.id.mVisibility);
            String visibility = atmosphere.getVisibility();
            mVisibility.setText(visibility+" km");
        }
        if (getItemViewType(position) == LAYOUT_3){
            TextView mSunrise = (TextView) holder.itemView.findViewById(R.id.mSunrise);
            String sunrise = astronomy.getSunrise();
            mSunrise.setText(sunrise);
        }
        if (getItemViewType(position) == LAYOUT_4){
            TextView mSunset = (TextView) holder.itemView.findViewById(R.id.mSunset);
            String sunset = astronomy.getSunset();
            mSunset.setText(sunset);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LAYOUT_1;
        }
        if (position == 1) {
            return LAYOUT_2;
        }
        if (position == 2) {
            return LAYOUT_3;
        }
        if (position == 3) {
            return LAYOUT_4;
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
