package com.example.dictionary;

import com.example.dictionary.Models.ApiResponce;

public interface OnFetchDataListener {
    void onFetchData(ApiResponce apiResponce, String message);
    void onError(String message);
}
