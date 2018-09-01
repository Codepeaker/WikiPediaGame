package com.codepeaker.wikipediagame.rest;


import com.codepeaker.wikipediagame.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    //    action:query
    //    generator:random
    //    grnnamespace:0
    //    prop:extracts
    //    exsentences:10
    //    explaintext:true
    //    exlimit:1
    //    format:json

    @GET(".")
    Call<ApiResponse> getRandomPage(@Query("action") String action,
                                    @Query("prop") String prop,
                                    @Query("explaintext") boolean explainText,
                                    @Query("exlimit") int exLimit,
                                    @Query("generator") String generator,
                                    @Query("grnnamespace") int grnnamespace,
                                    @Query("format") String format);

}
