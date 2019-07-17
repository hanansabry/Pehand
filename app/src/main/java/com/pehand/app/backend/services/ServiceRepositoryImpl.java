package com.pehand.app.backend.services;

import com.pehand.app.backend.retrofit.ApiClient;
import com.pehand.app.backend.retrofit.PehandEndpoints;
import com.pehand.app.pojos.City;
import com.pehand.app.pojos.Service;
import com.pehand.app.pojos.SliderImage;
import com.pehand.app.pojos.SubService;
import com.pehand.app.pojos.SubServiceDetails;
import com.pehand.app.pojos.Village;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRepositoryImpl implements ServicesRepository {

    private PehandEndpoints apiService;

    public ServiceRepositoryImpl() {
        apiService = ApiClient.getClient().create(PehandEndpoints.class);
    }

    @Override
    public void getAllServices(final ServiceCallback callback) {
        Call<List<Service>> call = apiService.getAllServices();
        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                ArrayList<Service> allServices = (ArrayList<Service>) response.body();
                callback.onSuccess(allServices);
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getAllSubServicesById(int id, final SubServicesRetrievingCallback callback) {
        Call<List<SubService>> call = apiService.getAllSubServicesById(id);
        call.enqueue(new Callback<List<SubService>>() {
            @Override
            public void onResponse(Call<List<SubService>> call, Response<List<SubService>> response) {
                ArrayList<SubService> subServices = (ArrayList<SubService>) response.body();
                callback.onSuccess(subServices);
            }

            @Override
            public void onFailure(Call<List<SubService>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getSubServiceDetailsById(int id, final SubServiceDetailsCallback callback) {
        Call<SubServiceDetails> call = apiService.getSubServiceDetailsById(id);
        call.enqueue(new Callback<SubServiceDetails>() {
            @Override
            public void onResponse(Call<SubServiceDetails> call, Response<SubServiceDetails> response) {
                SubServiceDetails subServiceDetails = response.body();
                callback.onSuccess(subServiceDetails);
            }

            @Override
            public void onFailure(Call<SubServiceDetails> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getAllOffers(final SubServicesRetrievingCallback callback) {
        Call<List<SubService>> call = apiService.getAllOffers();
        call.enqueue(new Callback<List<SubService>>() {
            @Override
            public void onResponse(Call<List<SubService>> call, Response<List<SubService>> response) {
                ArrayList<SubService> subServices = (ArrayList<SubService>) response.body();
                callback.onSuccess(subServices);
            }

            @Override
            public void onFailure(Call<List<SubService>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getSliderImages(final ImageSliderCallback callback) {
        Call<List<SliderImage>> call = apiService.getAllSliderImages();
        call.enqueue(new Callback<List<SliderImage>>() {
            @Override
            public void onResponse(Call<List<SliderImage>> call, Response<List<SliderImage>> response) {
                ArrayList<SliderImage> sliderImages = (ArrayList<SliderImage>) response.body();
                callback.onSuccess(sliderImages);
            }

            @Override
            public void onFailure(Call<List<SliderImage>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getAllCities(final CityRetrievingCallback callback) {
        Call<List<City>> call = apiService.getAllCity();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                ArrayList<City> cities = (ArrayList<City>) response.body();
                callback.onSuccess(cities);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getVillageOfCityById(int id, final VillageRetrievingCallback callback) {
        Call<List<Village>> call = apiService.getVillagesOfCityById(id);
        call.enqueue(new Callback<List<Village>>() {
            @Override
            public void onResponse(Call<List<Village>> call, Response<List<Village>> response) {
                ArrayList<Village> villages = (ArrayList<Village>) response.body();
                ArrayList<String> villageNames = new ArrayList<>();
                ArrayList<Integer> villageIds = new ArrayList<>();
                for (Village village : villages) {
                    villageNames.add(village.getVillageName());
                    villageIds.add(village.getId());
                }
                callback.onSuccess(villageNames, villageIds);
            }

            @Override
            public void onFailure(Call<List<Village>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
