package com.example.windows10timt.myweather.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Windows 10 TIMT on 1/11/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
