package com.pehand.app.pojos;

public class SubServiceDetails {

    private int id;
    private String subServiceName;
    private String currentPrice;
    private String photoName;
    private String description;
    private int pServiceId;
    private boolean active;
    private boolean isOffer;
    private String priceNote;

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
        return photoName;
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
}
