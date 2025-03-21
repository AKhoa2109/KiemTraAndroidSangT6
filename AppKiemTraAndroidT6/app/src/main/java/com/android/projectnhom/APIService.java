//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom;

import com.android.projectnhom.entity.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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
}
