package com.pehand.app.pojos;

public class SubService {

    private int id;
    private String subServiceName;
    private String currentPrice;
    private String priceNote;
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
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
