//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient1 extends BaseClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";
    //private static final String BASE_URL = "http://app.iotstar.vn:8080/appfoods/";

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
