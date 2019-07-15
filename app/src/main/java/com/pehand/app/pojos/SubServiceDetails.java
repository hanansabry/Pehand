package com.pehand.app.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.pehand.app.common.Constants;

public class SubServiceDetails implements Parcelable {

    @SerializedName("Id")
    private int id;
    @SerializedName("SubServiceName")
    private String subServiceName;
    @SerializedName("CurrentPrice")
    private String currentPrice;
    @SerializedName("PhotoName")
    private String photoName;
    @SerializedName("Description")
    private String description;
    @SerializedName("PServiceId")
    private int pServiceId;
    @SerializedName("Active")
    private boolean active;
    @SerializedName("IsOffer")
    private boolean isOffer;
    @SerializedName("PriceNote")
    private String priceNote;

    public SubServiceDetails(int id, String subServiceName, String currentPrice, String photoName, String description, int pServiceId, String priceNote) {
        this.id = id;
        this.subServiceName = subServiceName;
        this.currentPrice = currentPrice;
        this.photoName = photoName;
        this.description = description;
        this.pServiceId = pServiceId;
        this.priceNote = priceNote;
    }

    protected SubServiceDetails(Parcel in) {
        id = in.readInt();
        subServiceName = in.readString();
        currentPrice = in.readString();
        photoName = in.readString();
        description = in.readString();
        pServiceId = in.readInt();
        active = in.readByte() != 0;
        isOffer = in.readByte() != 0;
        priceNote = in.readString();
    }

    public static final Creator<SubServiceDetails> CREATOR = new Creator<SubServiceDetails>() {
        @Override
        public SubServiceDetails createFromParcel(Parcel in) {
            return new SubServiceDetails(in);
        }

        @Override
        public SubServiceDetails[] newArray(int size) {
            return new SubServiceDetails[size];
        }
    };

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

    public String getPhotoName() {
        Log.d("details photo" , Constants.BASE_PHOTO_URL + "SubService/" + id + "/" + photoName);
        return Constants.BASE_PHOTO_URL + "SubService/" + id + "/" + photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getpServiceId() {
        return pServiceId;
    }

    public void setpServiceId(int pServiceId) {
        this.pServiceId = pServiceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

    public String getPriceNote() {
        return priceNote;
    }

    public void setPriceNote(String priceNote) {
        this.priceNote = priceNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subServiceName);
        dest.writeString(currentPrice);
        dest.writeString(photoName);
        dest.writeString(description);
        dest.writeInt(pServiceId);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeByte((byte) (isOffer ? 1 : 0));
        dest.writeString(priceNote);
    }
}
