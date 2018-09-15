package com.codepeaker.wikipediagame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.codepeaker.wikipediagame.model.ApiResponse;
import com.codepeaker.wikipediagame.repository.RandomPageRepository;

public class RandomPageViewModel extends AndroidViewModel {

    public static final String TAG = RandomPageViewModel.class.getSimpleName();
    private LiveData<ApiResponse> pageLiveData;

    public RandomPageViewModel(@NonNull Application application) {
        super(application);
    }

    public void getRandomPage() {
        pageLiveData = RandomPageRepository.getInstance().getRandomPage();
    }

    public LiveData<ApiResponse> getPageLiveData() {
        return pageLiveData;
    }
}
