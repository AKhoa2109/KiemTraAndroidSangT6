//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom.retrofit;

import com.android.projectnhom.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient extends BaseClient {
    private static final String BASE_URL = "http://192.168.56.2:8080/";
    //private static final String BASE_URL = "http://app.iotstar.vn:8080/appfoods/";
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getApiService() {
        return getInstance().create(APIService.class);
    }
}
