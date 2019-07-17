package com.pehand.app.backend.services;

import com.pehand.app.pojos.City;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SliderImage;
import com.pehand.app.pojos.SubService;
import com.pehand.app.pojos.SubServiceDetails;
import com.pehand.app.pojos.Village;

import java.util.ArrayList;
import java.util.List;

public interface ServicesRepository {

    interface ServiceCallback {
        void onSuccess(ArrayList<Service> allServices);

        void onFailure(String msg);
    }

    interface SubServicesRetrievingCallback {
        void onSuccess(ArrayList<SubService> allSubServices);

        void onFailure(String msg);
    }

    interface SubServiceDetailsCallback{
        void onSuccess(SubServiceDetails subServiceDetails);

        void onFailure(String msg);
    }

    interface ImageSliderCallback {
        void onSuccess(ArrayList<SliderImage> sliderImages);

        void onFailure(String msg);
    }

    interface CityRetrievingCallback {
        void onSuccess(ArrayList<City> cities);

        void onFailure(String msg);
    }

    interface VillageRetrievingCallback {
        void onSuccess(ArrayList<String> villages, ArrayList<Integer> villageIds);

        void onFailure(String msg);
    }

    void getAllServices(ServiceCallback callback);

    void getAllSubServicesById(int id, SubServicesRetrievingCallback callback);

    void getSubServiceDetailsById(int id, SubServiceDetailsCallback callback);

    void getAllOffers(SubServicesRetrievingCallback callback);

    void getSliderImages(ImageSliderCallback callback);

    void getAllCities(CityRetrievingCallback callback);

    void getVillageOfCityById(int id, VillageRetrievingCallback callback);
}
