package com.example.windows10timt.myweather.model.model;

/**
 * Created by Nguyen Tien Long on 9/6/2017.
 */

public class SearchRespone {
    private int woeId;
    private double lat;
    private double lon;
    private String country;
    private String city;
    private String qualifiedName;

    public SearchRespone(int woeId, double lat, double lon, String country, String city, String qualifiedName) {
        this.woeId = woeId;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.city = city;
        this.qualifiedName = qualifiedName;
    }

    public int getWoeId() {
        return woeId;
    }

    public void setWoeId(int woeId) {
        this.woeId = woeId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }
}
