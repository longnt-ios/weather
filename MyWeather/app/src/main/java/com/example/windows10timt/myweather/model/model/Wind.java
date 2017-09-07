package com.example.windows10timt.myweather.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 TIMT on 12/13/2016.
 */

public class Wind {
    @SerializedName("chill")
    @Expose
    private String chill;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("speed")
    @Expose
    private String speed;

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Wind(String chill, String direction, String speed) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
    }
}
