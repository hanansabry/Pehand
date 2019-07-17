package com.pehand.app.pojos;

import com.google.gson.annotations.SerializedName;

public class Village {

    @SerializedName("Id")
    private int id;
    @SerializedName("VillageName")
    private String villageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}
