package com.pehand.app.pojos;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.pehand.app.common.Constants;

public class Service {

    @SerializedName("Id")
    private int id;
    @SerializedName("ServiceName")
    private String serviceName;
    @SerializedName("PhotoName")
    private String photoName;

    public Service() {
    }

    public Service(int id, String serviceName, String photoName) {
        this.id = id;
        this.serviceName = serviceName;
        this.photoName = photoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPhotoName() {
        Log.d("Photo_Name" , Constants.BASE_PHOTO_URL + "Service/" + id + "/" + photoName);
        return Constants.BASE_PHOTO_URL + "Service/" + id + "/" + photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
