package com.example.mapsimplementationusinggoogleapi;

public class ApiUtils {
    private ApiUtils(){

    }
    public static final String BASE_URL = "http://192.168.1.103:3000/";
    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
