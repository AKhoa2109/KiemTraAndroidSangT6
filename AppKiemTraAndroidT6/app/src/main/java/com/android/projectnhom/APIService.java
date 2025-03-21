//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom;

import com.android.projectnhom.entity.Category;
import com.android.projectnhom.entity.LoginRequest;
import com.android.projectnhom.entity.LoginResponse;
import com.android.projectnhom.entity.Product;
import com.android.projectnhom.entity.UserResponse;


import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
public interface APIService {
    @GET("categories")
    Call<List<Category>> getCategoriesAll();

    @POST("register")
    Call<ResponseBody> register(@Body Map<String, String> request);
    @POST("activate")
    Call<ResponseBody> activate(@Body Map<String, String> request);
    //Le Dinh Loc - 22110369
    @GET("users/{id}")
    Call<UserResponse> getUserApi(@Path("id") Long userId);
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    //Nguyễn Tuấn Thành - 22110418
    @GET("product/category/{categoryId}")
    Call<List<Product>> getProductsByCategory(@Path("categoryId") Long categoryId);

}
