package com.pehand.app.pojos;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.pehand.app.common.Constants;

public class SubService {

    @SerializedName("Id")
    private int id;
    @SerializedName("SubServiceName")
    private String subServiceName;
    @SerializedName("CurrentPrice")
    private String currentPrice;
    @SerializedName("PriceNote")
    private String priceNote;
    @SerializedName("PhotoName")
    private String photoName;

    public SubService(int id, String subServiceName, String currentPrice, String priceNote, String photoName) {
        this.id = id;
        this.subServiceName = subServiceName;
        this.currentPrice = currentPrice;
        this.priceNote = priceNote;
        this.photoName = photoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getPriceNote() {
        return priceNote;
    }

    public void setPriceNote(String priceNote) {
        this.priceNote = priceNote;
    }

    public String getPhotoName() {
        Log.d("Subservice photo name", Constants.BASE_PHOTO_URL + "SubService/" + id + "/" + photoName);
        return Constants.BASE_PHOTO_URL + "SubService/" + id + "/" + photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
