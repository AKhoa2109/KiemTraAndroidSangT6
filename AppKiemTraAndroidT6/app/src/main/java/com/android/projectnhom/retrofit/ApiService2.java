package com.android.projectnhom.retrofit;

import com.android.projectnhom.entity.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Le Dinh Loc - 22110369
public interface ApiService2 {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService2 apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService2.class);


    @GET("users/{id}")
    Call<UserResponse> getUserApi(@Path("id") Long userId);

}
