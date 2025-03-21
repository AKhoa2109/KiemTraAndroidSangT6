// Nguyễn Lý Hùng --22110337
package com.android.projectnhom.retrofit;

import com.android.projectnhom.APIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient extends BaseClient {
    private static final String BASE_URL = "http://192.168.1.13:8080/api/";
//   private static final String BASE_URL = "http://192.168.1.13:8080/api/";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            // Thêm logging (tùy chọn)
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create()) // Thêm ScalarsConverterFactory
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static APIService getApiService() {
        return getInstance().create(APIService.class);
    }
}
