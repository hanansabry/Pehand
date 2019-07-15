package com.pehand.app.pojos;

import com.google.gson.annotations.SerializedName;
import com.pehand.app.common.Constants;

public class SliderImage {

    @SerializedName("Id")
    private int id;
    @SerializedName("PhotoName")
    private String photoName;
    @SerializedName("LinkURL")
    private String linkUrl;

    public String getPhotoName() {
        return Constants.BASE_PHOTO_URL + "HomeSlider/" + id + "/" + photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
