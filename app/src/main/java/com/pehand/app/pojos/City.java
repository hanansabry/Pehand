package com.pehand.app.pojos;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("Id")
    private int id;
    @SerializedName("CityName")
    private String cityName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
