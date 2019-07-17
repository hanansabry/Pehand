package com.pehand.app.backend.services;

import java.util.Map;

public interface PostDataRepository {

    interface PostRequestCallback {
        void onSuccess();
        void onFailure(String msg);
    }

    void contactUs(Map<String, String> data, PostRequestCallback callback);

    void orderNow(Map<String, String> data, PostRequestCallback callback);
}
