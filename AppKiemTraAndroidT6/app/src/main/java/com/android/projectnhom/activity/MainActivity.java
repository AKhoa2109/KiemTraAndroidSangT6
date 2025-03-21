package com.android.projectnhom.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.projectnhom.APIService;
import com.android.projectnhom.R;
import com.android.projectnhom.adapter.CategoryAdapter;
import com.android.projectnhom.entity.Category;
import com.android.projectnhom.entity.UserResponse;
import com.android.projectnhom.retrofit.ApiService;
import com.android.projectnhom.retrofit.ApiService2;
import com.android.projectnhom.retrofit.RetrofitClient1;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcCate;
    // Khai báo Adapter
    CategoryAdapter categoryAdapter;
    APIService apiService;

    ImageView imgProfile;
    TextView txtUsername;

    List<Category> categoryList = new ArrayList<>(); // Khởi tạo danh sách
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sửa lỗi tên layout

        AnhXa(); // Gọi hàm ánh xạ
        GetCategory(); // Load dữ liệu cho danh mục
        getUser(Long.valueOf(1));
    }
    @SuppressLint("WrongViewCast")
    private void AnhXa() {
        // Ánh xạ RecyclerView từ layout
        rcCate = findViewById(R.id.recyclerCategories);

        imgProfile = findViewById(R.id.imgProfile);
        txtUsername = findViewById(R.id.txtUsername);
    }
    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient1.getInstance().create(APIService.class);
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList = response.body();

                    // 🚀 Khởi tạo Adapter TRƯỚC KHI gán vào RecyclerView
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rcCate.setAdapter(categoryAdapter);

                    // ⚡ Gán LayoutManager sau khi Adapter đã có dữ liệu
                    rcCate.setHasFixedSize(true);
                    rcCate.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("Retrofit", "Lỗi kết nối: " + t.getMessage());
            }
        });

    }

    //Le Dinh Loc - 22110369
    private void getUser(Long i) {
        ApiService2.apiService.getUserApi(i).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();

                if(userResponse == null){
                    return;
                }
                txtUsername.setText(userResponse.getName());
                // Load ảnh từ URL vào ImageView bằng Glide
                Glide.with(MainActivity.this)
                        .load(userResponse.getImage())
                        .placeholder(R.drawable.profile_3135715) // Hình ảnh mặc định nếu không có ảnh
                        .into(imgProfile);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error getUser ", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}