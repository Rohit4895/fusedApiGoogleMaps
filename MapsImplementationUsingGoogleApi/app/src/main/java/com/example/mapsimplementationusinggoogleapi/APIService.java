package com.example.mapsimplementationusinggoogleapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("addLocation")
    Call<Loctn> sendLocation(@Body Loctn loctn);
}
