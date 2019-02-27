package com.example.mapsimplementationusinggoogleapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loctn {

    @Expose
    @SerializedName("longitude")
    private String longitude;
    @Expose
    @SerializedName("latitude")
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
