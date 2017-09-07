package com.example.windows10timt.myweather.sqlite;

/**
 * Created by Windows 10 TIMT on 1/11/2017.
 */

public class SQLProduct {
    public String city;
    public String temp;
    public String speed;
    public String humidity;
    public String pressure;


    public SQLProduct(String city, String temp, String speed, String humidity, String pressure) {
        this.city = city;
        this.temp = temp;
        this.speed = speed;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
