package com.example.windows10timt.myweather.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 TIMT on 12/15/2016.
 */

public class PostApi {
    @SerializedName("lat")
    private float lat;
    @SerializedName("lon")
    private float lon;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("appid")
    private String appid;

    public PostApi(float lat, float lon, int cnt, String appid) {
        this.lat = lat;
        this.lon = lon;
        this.cnt = cnt;
        this.appid = appid;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
