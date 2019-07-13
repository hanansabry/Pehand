package com.pehand.app.backend.retrofit;

import com.pehand.app.pojos.City;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SliderImage;
import com.pehand.app.pojos.SubService;
import com.pehand.app.pojos.SubServiceDetails;
import com.pehand.app.pojos.Village;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PehandEndpoints {

    @GET("AllService")
    Call<List<Service>> getAllServices();

    @GET("AllSubService/{id}")
    Call<List<SubService>> getAllSubServicesById(@Path("id") int id);

    @GET("AllSubServiceDetails/{id}")
    Call<SubServiceDetails> getSubServiceDetailsById(@Path("id") int id);

    @GET("AllOffer")
    Call<List<SubService>> getAllOffers();

    @GET("AllPhotoSlider")
    Call<List<SliderImage>> getAllSliderImages();

    @GET("AllCity")
    Call<List<City>> getAllCity();

    @GET("AllCity/{id}")
    Call<List<Village>> getVillagesOfCityById(@Path("id") int id);
}
