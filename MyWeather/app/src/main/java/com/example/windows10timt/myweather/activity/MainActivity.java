package com.example.windows10timt.myweather.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.adapter.AdapterMylist;
import com.example.windows10timt.myweather.listener.GithubClient;
import com.example.windows10timt.myweather.listener.UserService;
import com.example.windows10timt.myweather.model.model.Astronomy;
import com.example.windows10timt.myweather.model.model.Atmosphere;
import com.example.windows10timt.myweather.model.model.Condition;
import com.example.windows10timt.myweather.model.model.Data;
import com.example.windows10timt.myweather.model.model.Item;
import com.example.windows10timt.myweather.model.model.ProductForecast;
import com.example.windows10timt.myweather.model.model.Results;
import com.example.windows10timt.myweather.network.RetrofitClient;
import com.example.windows10timt.myweather.network.RetrofitClientWeather;
import com.example.windows10timt.myweather.model.model.Wind;
import com.example.windows10timt.myweather.model.model.Example;
import com.example.windows10timt.myweather.sqlite.SQLHelper;
import com.example.windows10timt.myweather.sqlite.SQLHelper2;
import com.example.windows10timt.myweather.sqlite.SQLProduct;
import com.example.windows10timt.myweather.sqlite.SQLProduct2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener {

    private TextView mTextName;
    private AdapterMylist adapterMylist;
    private ArrayList<ProductForecast> product;
    private RecyclerView mList;
    private Condition conditions;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Wind wind;
    private Item item;
    private ImageView mImageSearch;
    private Results results;
    private boolean abc;
    private ProgressDialog mPdLoading;
    private com.example.windows10timt.myweather.model.model.Location location2;
    private SQLHelper sqlHelper;
    private SQLHelper2 sqlHelper2;
    private ArrayList<SQLProduct> sqlProducts;
    private ArrayList<SQLProduct2> sqlProducts2;
    private String myCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextName = (TextView) findViewById(R.id.mTextName);
        mList = (RecyclerView) findViewById(R.id.mList);
        mList.setHorizontalScrollBarEnabled(false);
        mImageSearch = (ImageView) findViewById(R.id.imageSearch);
        mImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                final String mCountry = "";
                if (myCity != null && !myCity.isEmpty()) {
                    UserService userService = RetrofitClientWeather.getGithubServiceWeather();
                    Call<Data> callData = userService.getTask("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\" " + myCity + "," + mCountry + "\")", "json");
                    callData.enqueue(new Callback<Data>() {
                        @Override
                        public void onResponse(Call<Data> call, Response<Data> response) {
                            if (response.isSuccessful()) {

                                results = response.body().getProduct().getResults();
                                if (results != null) {

                                    product = response.body().getProduct().getResults().getChannel().getItem().getForecast();
                                    conditions = response.body().getProduct().getResults().getChannel().getItem().getCondition();
                                    String name = response.body().getProduct().getResults().getChannel().getLocation().getCity();
                                    mTextName.setText(name);
                                    atmosphere = response.body().getProduct().getResults().getChannel().getAtmosphere();
                                    astronomy = response.body().getProduct().getResults().getChannel().getAstronomy();
                                    wind = response.body().getProduct().getResults().getChannel().getWind();
                                    item = response.body().getProduct().getResults().getChannel().getItem();
                                    location2 = response.body().getProduct().getResults().getChannel().getLocation();
                                    boolean abc = getIntent().getBooleanExtra("check", true);

                                    mList.setHasFixedSize(true);
                                    adapterMylist = new AdapterMylist(conditions, atmosphere, astronomy, wind, product, getApplicationContext(), item, MainActivity.this, location2);
                                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);

                                    mList.setLayoutManager(manager);
                                    mList.setItemAnimator(new DefaultItemAnimator());
                                    mList.setAdapter(adapterMylist);
                                    String mTemp = conditions.getTemp();
                                    sqlHelper2 = new SQLHelper2(getApplicationContext());

                                    for (int i = 1; i < product.size(); i++) {
                                        String day = product.get(i).getDay();
                                        String date = product.get(i).getDate();
                                        String high = product.get(i).getHigh();
                                        String low = product.get(i).getLow();
                                        String description = product.get(i).getText();
                                        sqlHelper2.insertWeather2(day, date, description, high, low);
                                        sqlHelper2.updateWeather2(i, day, date, description, high, low);
                                    }

                                    sqlHelper = new SQLHelper(getApplicationContext());
                                    sqlHelper.insertWeather(name, mTemp, wind.getSpeed(), atmosphere.getHumidity(), atmosphere.getPressure());
                                    sqlHelper.updateWeather(1, name, mTemp, wind.getSpeed(), atmosphere.getHumidity(), atmosphere.getPressure());


                                    adapterMylist.notifyDataSetChanged();
                                } else {
                                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Không tìm thấy địa chỉ !")
                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                }
                                            })
                                            .create();

                                    dialog.show();


                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<Data> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "don't find location", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Location mLocation = getMylocation();
                    if (mLocation != null) {

                        mPdLoading = new ProgressDialog(MainActivity.this);
                        mPdLoading.setMessage("Connecting");
                        mPdLoading.setCanceledOnTouchOutside(false);
                        mPdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        mPdLoading.onStart();
                        mPdLoading.show();
                        double latitude = mLocation.getLatitude();
                        double longitude = mLocation.getLongitude();

                        GithubClient client = RetrofitClient.getGithubService();

                        Call<Example> call = client.getUser(latitude, longitude, 1, "be8d3e323de722ff78208a7dbb2dcd6f");
                        call.enqueue(new Callback<Example>() {
                            @Override
                            public void onResponse(Call<Example> call, Response<Example> response) {
                                if (response.isSuccessful()) {
                                    mPdLoading.dismiss();
                                    final String city = response.body().getCity().getName();
                                    String country = response.body().getCity().getCountry();

                                    UserService userService = RetrofitClientWeather.getGithubServiceWeather();
                                    Call<Data> callData = userService.getTask("select * from weather.forecast where woeid in (select woeid from geo.places(5) where text=\" " + city + "," + country + "\")", "json");


                                    callData.enqueue(new Callback<Data>() {
                                        @Override
                                        public void onResponse(Call<Data> call, Response<Data> response) {

                                            results = response.body().getProduct().getResults();

                                            if (results != null) {
                                                results = response.body().getProduct().getResults();

                                                item = results.getChannel().getItem();
                                                product = item.getForecast();
                                                conditions = item.getCondition();
                                                String name = results.getChannel().getLocation().getCity();
                                                mTextName.setText(name);
                                                atmosphere = results.getChannel().getAtmosphere();
                                                astronomy = results.getChannel().getAstronomy();
                                                wind = results.getChannel().getWind();
                                                location2 = results.getChannel().getLocation();
                                                Log.d("main_activity", location2.getCity());
                                                mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                mList.setItemAnimator(new DefaultItemAnimator());
                                                mList.setHasFixedSize(true);
                                                adapterMylist = new AdapterMylist(conditions, atmosphere, astronomy, wind, product, getApplicationContext(), item, MainActivity.this, location2);
                                                mList.setAdapter(adapterMylist);


                                                String mTemp = conditions.getTemp();
                                                String mDescription = conditions.getText();
                                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                                SharedPreferences.Editor editor = preferences.edit();
                                                editor.putString("city", name);
                                                editor.putString("description", mDescription);
                                                editor.putString("temp", mTemp);
                                                abc = getIntent().getBooleanExtra("check", true);
                                                editor.putBoolean("checkWidget", abc);
                                                editor.apply();


                                                sqlHelper2 = new SQLHelper2(getApplicationContext());

                                                for (int i = 1; i < product.size(); i++) {
                                                    String day = product.get(i).getDay();
                                                    String date = product.get(i).getDate();
                                                    String high = product.get(i).getHigh();
                                                    String low = product.get(i).getLow();
                                                    String description = product.get(i).getText();
                                                    sqlHelper2.insertWeather2(day, date, description, high, low);
                                                    sqlHelper2.updateWeather2(i, day, date, description, high, low);

                                                }

                                                sqlHelper = new SQLHelper(getApplicationContext());
                                                sqlHelper.insertWeather(name, mTemp, wind.getSpeed(), atmosphere.getHumidity(), atmosphere.getPressure());
                                                sqlHelper.updateWeather(1, name, mTemp, wind.getSpeed(), atmosphere.getHumidity(), atmosphere.getPressure());


                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Data> call, Throwable t) {
//                                    mPdLoading.dismiss();
                                            Log.d("erro", "onResponse: " + t.toString());
                                        }
                                    });

                                } else {
                                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Example> call, Throwable t) {
                                Log.d("error", "onFailure: " + t.toString());
//                        mPdLoading.dismiss();
                            }
                        });
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hãy bật GPS hoặc tìm địa chỉ cụ thể !")
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                })
                                .create();

                        dialog.show();

                    }

                }

            } else {
                sqlHelper = new SQLHelper(getApplicationContext());
                sqlHelper2 = new SQLHelper2(getApplicationContext());
                sqlProducts = sqlHelper.getAllWeather();
                sqlProducts2 = sqlHelper2.getAllWeather2();
                SQLProduct sqlProduct = sqlProducts.get(0);

                ArrayList<ProductForecast> forecasts = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    SQLProduct2 sqlProduct2 = sqlProducts2.get(i);
                    ProductForecast forecast = new ProductForecast("", sqlProduct2.date, sqlProduct2.day, sqlProduct2.high, sqlProduct2.low, sqlProduct2.description);
                    forecasts.add(forecast);
                }
                SQLProduct2 sqlCondition = sqlProducts2.get(0);
                product = forecasts;
                conditions = new Condition("", sqlCondition.date, sqlProduct.temp, sqlCondition.description);
                atmosphere = new Atmosphere(sqlProduct.humidity, sqlProduct.pressure, "", "100");
                astronomy = new Astronomy("7:20 am", "4:49 pm");
                mTextName.setText(sqlProduct.city);
                wind = new Wind("", "", sqlProduct.speed);
//                ProductForecast forecast = new ProductForecast("", sqlProduct.date, sqlProduct.day, sqlProduct.high, sqlProduct.low, sqlProduct.description);
//                ArrayList<ProductForecast> forecasts = new ArrayList<>();
//                forecasts.add(forecast);

                item = new Item("", "0", "0");
                location2 = new com.example.windows10timt.myweather.model.model.Location(sqlProduct.city);
                mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mList.setItemAnimator(new DefaultItemAnimator());
                mList.setHasFixedSize(true);
                adapterMylist = new AdapterMylist(conditions, atmosphere, astronomy, wind, product, getApplicationContext(), item, MainActivity.this, location2);
                mList.setAdapter(adapterMylist);

            }

        }

    };

    @Override
    protected void onResume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction(MainActivity.CONNECTIVITY_SERVICE);
        registerReceiver(receiver, filter);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public String getEnabledLocationProvider(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        String bestProvide = null;
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (network_enabled) {
            bestProvide = LocationManager.NETWORK_PROVIDER;
        } else if (gps_enabled) {
            bestProvide = LocationManager.GPS_PROVIDER;
        }
        return bestProvide;
    }

    private Location getMylocation() {
        String provider = getEnabledLocationProvider(getBaseContext());
        if (provider == null) return null;
        else {
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);


            try {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000, 10, (LocationListener) this);
                Location mylocation = locationManager.getLastKnownLocation(provider);
                return mylocation;
            } catch (SecurityException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                myCity = data.getStringExtra("city");

                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}

