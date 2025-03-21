//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom;

import com.android.projectnhom.entity.Category;
import com.android.projectnhom.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("categories")
    Call<List<Category>> getCategoriesAll();
//    @GET("category.php")
//    Call<CategoryModel> getCategory();
//
//    @POST("/v1/user")
//    @FormUrlEncoded
//    Call<User> login(@Field("username") String username,
//                     @Field("password") String password);

    //Nguyễn Tuấn Thành - 22110418
    @GET("product/category/{categoryId}")
    Call<List<Product>> getProductsByCategory(@Path("categoryId") Long categoryId);
}
