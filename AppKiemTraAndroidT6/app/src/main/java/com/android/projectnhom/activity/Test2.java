//Nguyễn Lý Hùng - 22110337
package com.android.projectnhom.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.projectnhom.APIService;
import com.android.projectnhom.R;
import com.android.projectnhom.adapter.CategoryAdapter;
import com.android.projectnhom.entity.Category;
import com.android.projectnhom.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test2 extends AppCompatActivity {

    RecyclerView rcCate;
    // Khai báo Adapter
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList = new ArrayList<>(); // Khởi tạo danh sách

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2); // Sửa lỗi tên layout

        AnhXa(); // Gọi hàm ánh xạ
        GetCategory(); // Load dữ liệu cho danh mục
    }

    private void AnhXa() {
        // Ánh xạ RecyclerView từ layout
        rcCate = findViewById(R.id.recyclerCategories);
    }
    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient.getInstance().create(APIService.class);
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList = response.body();

                    // 🚀 Khởi tạo Adapter TRƯỚC KHI gán vào RecyclerView
                    categoryAdapter = new CategoryAdapter(Test2.this, categoryList);
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

}