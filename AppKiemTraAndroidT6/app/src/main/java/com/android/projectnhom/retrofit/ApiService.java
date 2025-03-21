package com.android.projectnhom.retrofit;

import com.android.projectnhom.entity.LoginRequest;
import com.android.projectnhom.entity.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/*Nguyễn Hoàng Anh Khoa - 22110352*/
public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    /*Toan*/
    @POST("api/users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    /*Toan*/
}
