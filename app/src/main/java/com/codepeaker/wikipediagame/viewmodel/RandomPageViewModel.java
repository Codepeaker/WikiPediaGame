package com.codepeaker.wikipediagame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.codepeaker.wikipediagame.model.ApiResponse;
import com.codepeaker.wikipediagame.rest.RestClient;
import com.codepeaker.wikipediagame.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomPageViewModel extends AndroidViewModel {

    public static final String TAG = RandomPageViewModel.class.getSimpleName();
    private final Application application;

    private LiveData<ApiResponse> pageLiveData;



    public RandomPageViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        pageLiveData = getRandomPage();
    }

    public LiveData<ApiResponse> getRandomPage() {
        AppUtils.showPleaseWaitDialog(application);

        Call<ApiResponse> call = RestClient.getRestInterface().getRandomPage(
                "query", "extracts", true, 1
                , "random", 0, "json");

        final MutableLiveData<ApiResponse> pageMutableLiveData = new MutableLiveData<>();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                pageMutableLiveData.setValue(response.body());
                setPageLiveData(pageMutableLiveData);
                AppUtils.hidePDialog();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                pageMutableLiveData.setValue(null);
                AppUtils.hidePDialog();
            }
        });

        return pageMutableLiveData;

    }

    public LiveData<ApiResponse> getPageLiveData() {
        return pageLiveData;
    }

    public void setPageLiveData(LiveData<ApiResponse> pageLiveData) {
        this.pageLiveData = pageLiveData;
    }
}
