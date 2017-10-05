package com.example.windows10timt.myweather.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;

import android.widget.TextView;

import com.example.windows10timt.myweather.activity.MainActivity;
import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.activity.MapsActivity;
import com.example.windows10timt.myweather.model.model.Location;
import com.example.windows10timt.myweather.model.model.Astronomy;
import com.example.windows10timt.myweather.model.model.Atmosphere;
import com.example.windows10timt.myweather.model.model.Condition;
import com.example.windows10timt.myweather.model.model.Item;
import com.example.windows10timt.myweather.model.model.ProductForecast;
import com.example.windows10timt.myweather.model.model.Wind;
import com.example.windows10timt.myweather.fragment.MapsFragment;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;


/**
 * Created by Windows 10 TIMT on 12/20/2016.
 */

public class AdapterMylist extends RecyclerView.Adapter<AdapterMylist.MyViewHolder> {

    private Condition conditions;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Wind wind;
    private ArrayList<ProductForecast> productForecasts;
    private Context context;
    private Item item;
    MainActivity mActivity;
    private MapsFragment mapsFragment;
    private Location location2;

    public AdapterMylist(Condition conditions, Atmosphere atmosphere, Astronomy astronomy, Wind wind, ArrayList<ProductForecast> productForecasts, Context context, Item item, MainActivity mActivity, Location location2) {
        this.conditions = conditions;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.wind = wind;
        this.productForecasts = productForecasts;
        this.context = context;
        this.item = item;
        this.mActivity = mActivity;
        this.location2 = location2;
    }

    private static final int LAYOUT_1 = 0;
    private static final int LAYOUT_2 = 1;
    private static final int LAYOUT_3 = 2;
    private static final int LAYOUT_4 = 3;
    private static final int LAYOUT_5 = 4;
    private static final int LAYOUT_6 = 5;
    private static final int LAYOUT_7 = 6;
    private static final int LAYOUT_8 = 7;
    private static final int LAYOUT_9 = 8;
    private static final int LAYOUT_10 = 9;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LAYOUT_1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout2, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_layout, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout2, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout3, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_6) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout2, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_map_layout, parent, false);

            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout2, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == LAYOUT_9) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout4, parent, false);
            return new MyViewHolder(view);
        } else {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
            return new MyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == LAYOUT_1) {

            ProductForecast item = productForecasts.get(0);
            ImageView mImageCloud = (ImageView) holder.itemView.findViewById(R.id.mImageCloud);
            TextView mTvTemp = (TextView) holder.itemView.findViewById(R.id.mTvTemp);
            TextView mTvDescription = (TextView) holder.itemView.findViewById(R.id.mTvDescription);
            TextView mHigh = (TextView) holder.itemView.findViewById(R.id.mHigh);
            TextView mLow = (TextView) holder.itemView.findViewById(R.id.mLow);
            TextView mWeatherDay = (TextView) holder.itemView.findViewById(R.id.mWeatherDay);
            String day = item.getDate();
            String temp = conditions.getTemp();
            String description = conditions.getText();
            String high = item.getHigh();
            String low = item.getLow();


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mActivity.getApplicationContext());
            boolean check = preferences.getBoolean("check", true);

            if (check) {
                int temp2 = Integer.valueOf(temp);
                int high2 = Integer.valueOf(high);
                int low2 = Integer.valueOf(low);

                int newTemp = (int) Math.round((temp2 - 32) / 1.8);
                int newHigh = (int) Math.round((high2 - 32) / 1.8);
                int newLow = (int) Math.round((low2 - 32) / 1.8);

                String mTemp = String.valueOf(newTemp);
                String myHigh = String.valueOf(newHigh);
                String myLow = String.valueOf(newLow);

                mTvTemp.setText(mTemp + "°C");

                SpannableString spannableStringRed = new SpannableString("↥" + myHigh + "°");
                spannableStringRed.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                SpannableString spannableStringBlue = new SpannableString("↧" + myLow + "°");
                spannableStringBlue.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mHigh.setText(spannableStringRed);
                mLow.setText(spannableStringBlue);
            }
            if (!check) {
                mTvTemp.setText(temp + "°F");

                SpannableString spannableStringRed = new SpannableString("↥" + high + "°");
                spannableStringRed.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                SpannableString spannableStringBlue = new SpannableString("↧" + low + "°");
                spannableStringBlue.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mHigh.setText(spannableStringRed);
                mLow.setText(spannableStringBlue);
            }

            mTvDescription.setText(description);
            description.replace(" ", "%20");
            mWeatherDay.setText(day);
            switch (description) {
                case "Showers":
                case "Scattered Showers":
                    mImageCloud.setImageResource(R.drawable.rain);
                    break;
                case "Breezy":
                    mImageCloud.setImageResource(R.drawable.breezy);
                    break;
                case "Sunny":
                case "Mostly Sunny":
                    mImageCloud.setImageResource(R.drawable.sunny);
                    break;
                case "Partly Cloudy":
                case "Cloudy":
                case "Mostly Cloudy":
                case "Mostly Clear":
                    mImageCloud.setImageResource(R.drawable.cloud_much);
                    break;
                case "Rain":
                case "Heavy Rain":
                    mImageCloud.setImageResource(R.drawable.rain);
                    break;
            }
        }

        if (getItemViewType(position) == LAYOUT_2) {

        }
        if (getItemViewType(position) == LAYOUT_3) {

            RecyclerView listForecast = (RecyclerView) holder.itemView.findViewById(R.id.listForecast);
            listForecast.setHasFixedSize(true);
            ArrayList<ProductForecast> item = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                item.add(productForecasts.get(i));
            }
            ForecastAdapter adapterMylist = new ForecastAdapter(context, item);
            LinearLayoutManager manager = new LinearLayoutManager(context);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listForecast.getContext(), manager.getOrientation());
            listForecast.addItemDecoration(dividerItemDecoration);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            listForecast.setLayoutManager(manager);
            listForecast.setAdapter(adapterMylist);

        }

        if (getItemViewType(position) == LAYOUT_4) {
            TextView mTitle = (TextView) holder.itemView.findViewById(R.id.mTitle);
            mTitle.setText("Details");
        }

        if (getItemViewType(position) == LAYOUT_5) {
            String description = conditions.getText();
            ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.imageDetails);
            switch (description) {
                case "Showers":
                case "Scattered Showers":
                    imageView.setImageResource(R.drawable.snow);
                    break;
                case "Breezy":
                    imageView.setImageResource(R.drawable.breezy);
                    break;
                case "Sunny":
                case "Mostly Sunny":
                    imageView.setImageResource(R.drawable.sunny);
                    break;
                case "Partly Cloudy":
                case "Cloudy":
                case "Mostly Cloudy":
                    imageView.setImageResource(R.drawable.cloud_much);
                    break;
                case "Rain":
                case "Heavy Rain":
                    imageView.setImageResource(R.drawable.rain);
                    break;
            }

            RecyclerView layout = (RecyclerView) holder.itemView.findViewById(R.id.mLayout5);
            layout.setHasFixedSize(true);
            AdapterLayout5 adapterLayout5 = new AdapterLayout5(atmosphere, astronomy);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            layout.setLayoutManager(manager);
            layout.setAdapter(adapterLayout5);
        }
        if (getItemViewType(position) == LAYOUT_6) {
            TextView mTitle = (TextView) holder.itemView.findViewById(R.id.mTitle);
            mTitle.setText("Maps");
        }
        if (getItemViewType(position) == LAYOUT_7) {
//            FragmentManager fm = mActivity.getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            if (fm.findFragmentById(R.id.framelayout) == null) {
//                mapsFragment = getMapFragment();
//                MapsFragment fragment = MapsFragment.newInstance(item, location2);
//                ft.replace(R.id.framelayout, fragment);
//                ft.commit();
//            }

            String urlMap = "https://maps.googleapis.com/maps/api/staticmap?center=" + item.getLat() + "," + item.getLon() + "&zoom=15&size=400x400&markers=color:red%7Clabel:C%7C" + item.getLat() + "," + item.getLon();
            ImageView imageMap = (ImageView) holder.itemView.findViewById(R.id.imageGoogleMap);
            Picasso.with(context).load(urlMap).into(imageMap);

            imageMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), MapsActivity.class);
                    intent.putExtra("latitude1", item.getLat().trim());
                    intent.putExtra("longitude1", item.getLon().trim());
                    intent.putExtra("city1", location2.getCity().trim());
                    mActivity.startActivityForResult(intent, 99);
                }
            });
        }
        if (getItemViewType(position) == LAYOUT_8) {
            TextView mTitle = (TextView) holder.itemView.findViewById(R.id.mTitle);
            mTitle.setText("Wind");
        }
        if (getItemViewType(position) == LAYOUT_9) {
            TextView mSpeed = (TextView) holder.itemView.findViewById(R.id.mSpeed);
            ImageView mAnimation = (ImageView) holder.itemView.findViewById(R.id.mAnimation);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.clockwise);
            int mySpeed = Integer.valueOf(wind.getSpeed());
            if (mySpeed == 0) {
                animation.setDuration(0);
            } else {
                animation.setDuration(20000 / mySpeed);
            }
            mAnimation.startAnimation(animation);
            String speed = wind.getSpeed();
            mSpeed.setText(speed + " km/h");
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
        if (position == 4) {
            return LAYOUT_5;
        }
        if (position == 5) {
            return LAYOUT_6;
        }
        if (position == 6) {
            return LAYOUT_7;
        }
        if (position == 7) {
            return LAYOUT_8;
        }
        if (position == 8) {
            return LAYOUT_9;
        }
        if (position == 9) {
            return LAYOUT_10;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 9;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

    }

    public MapsFragment getMapFragment() {
        if (mapsFragment == null) mapsFragment = new MapsFragment();
        return mapsFragment;
    }
}
