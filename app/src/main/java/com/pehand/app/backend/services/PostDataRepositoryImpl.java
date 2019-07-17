package com.pehand.app.backend.services;

import com.pehand.app.backend.retrofit.ApiClient;
import com.pehand.app.backend.retrofit.PehandEndpoints;
import com.pehand.app.common.Constants;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataRepositoryImpl implements PostDataRepository {

    private PehandEndpoints apiService;

    public PostDataRepositoryImpl() {
        apiService = ApiClient.getClient().create(PehandEndpoints.class);
    }

    @Override
    public void contactUs(Map<String, String> data, final PostRequestCallback callback) {
        Call<ResponseBody> call = apiService.contactUs(data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();
                if (statusCode == Constants.STATUS_OK) {
                    callback.onSuccess();
                } else {
                    callback.onFailure("Status code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void orderNow(Map<String, String> data, final PostRequestCallback callback) {
        Call<ResponseBody> call = apiService.orderNow(data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();
                if (statusCode == Constants.STATUS_OK) {
                    callback.onSuccess();
                } else {
                    callback.onFailure("Status code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
