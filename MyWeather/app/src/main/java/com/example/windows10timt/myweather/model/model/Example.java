package com.example.windows10timt.myweather.model.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 TIMT on 12/15/2016.
 */

public class Example {
    @SerializedName("city")
    @Expose
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
