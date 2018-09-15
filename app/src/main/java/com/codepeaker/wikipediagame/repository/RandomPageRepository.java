package com.codepeaker.wikipediagame.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.codepeaker.wikipediagame.model.ApiResponse;
import com.codepeaker.wikipediagame.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomPageRepository {

    private static RandomPageRepository randomPageRepository;
    private final MutableLiveData<ApiResponse> pageMutableLiveData = new MutableLiveData<>();

    public LiveData<ApiResponse> getRandomPage() {

        Call<ApiResponse> call = RestClient.getRestInterface().getRandomPage(
                "query", "extracts", true, 1
                , "random", 0, "json");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                pageMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                pageMutableLiveData.setValue(null);
            }
        });

        return pageMutableLiveData;

    }

    public synchronized static RandomPageRepository getInstance() {
        if (randomPageRepository == null) {
            randomPageRepository = new RandomPageRepository();
        }
        return randomPageRepository;
    }
}
