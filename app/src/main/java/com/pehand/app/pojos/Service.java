package com.pehand.app.pojos;

public class Service {

    private int id;
    private String serviceName;
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
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
