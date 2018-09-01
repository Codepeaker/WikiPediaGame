package com.codepeaker.wikipediagame.rest;

import com.codepeaker.wikipediagame.utils.ApiConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestInterface restInterface;

    public static RestInterface getRestInterface(){

        if (restInterface==null){

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            if (ApiConstant.showLog) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            restInterface = retrofit.create(RestInterface.class);
        }
        return restInterface;
    }

}
