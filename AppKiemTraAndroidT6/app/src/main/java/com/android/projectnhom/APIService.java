//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom;

import com.android.projectnhom.entity.Category;
import com.android.projectnhom.entity.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
public interface APIService {
    @GET("categories")
    Call<List<Category>> getCategoriesAll();

    @POST("/api/register")
    Call<String> register(@Body Map<String, String> request);

    @POST("/api/activate")
    Call<String> activate(@Body Map<String, String> request);

    //Nguyễn Tuấn Thành - 22110418
    @GET("product/category/{categoryId}")
    Call<List<Product>> getProductsByCategory(@Path("categoryId") Long categoryId);
}
