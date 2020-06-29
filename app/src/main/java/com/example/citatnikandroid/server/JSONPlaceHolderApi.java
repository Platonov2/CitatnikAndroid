package com.example.citatnikandroid.server;

import com.example.citatnikandroid.models.Citata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @GET("citatas")
    Call<ArrayList<Citata>> getAllCitatas();

    @GET("citatas/{id}")
    Call<Citata> getPostWithID(@Path("id") int id);

    @POST("citatas")
    Call<Citata> postCitata(@Body Citata data);
}
