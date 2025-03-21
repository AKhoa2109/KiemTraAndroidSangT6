package com.android.projectnhom.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.projectnhom.APIService;
import com.android.projectnhom.R;
import com.android.projectnhom.adapter.CategoryAdapter;
import com.android.projectnhom.adapter.ProductAdapter;
import com.android.projectnhom.entity.Category;
import com.android.projectnhom.entity.Product;
import com.android.projectnhom.retrofit.RetrofitClient1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcCategory, rcProduct;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private List<Category> categoryList = new ArrayList<>();
    private List<Product> allProducts = new ArrayList<>();
    private List<Product> visibleProducts = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private Button btnLoadMore;

    private int itemPerPage = 10;
    private int currentPage = 0;
    private boolean isLoading = false;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fetchCategories();
        fetchProducts(Long.valueOf(2));
    }

    private void initViews() {
        rcCategory = findViewById(R.id.recyclerCategories);
        rcProduct = findViewById(R.id.rc_lastproduct);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        progressBar = findViewById(R.id.progressBar);
        btnLoadMore = findViewById(R.id.btn_load_more);

        // Thiết lập RecyclerView cho danh mục sản phẩm (Nằm ngang)
        categoryAdapter = new CategoryAdapter(this, categoryList, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(int categoryId) {
                fetchProducts(Long.valueOf(categoryId));
            }
        });

        rcCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcCategory.setAdapter(categoryAdapter);

        // Thiết lập RecyclerView cho danh sách sản phẩm (Dạng lưới)
        productAdapter = new ProductAdapter(this, visibleProducts);
        rcProduct.setLayoutManager(new GridLayoutManager(this, 3));
        rcProduct.setAdapter(productAdapter);

        apiService = RetrofitClient1.getInstance().create(APIService.class);

        // Sự kiện kéo để làm mới
        swipeRefreshLayout.setOnRefreshListener(() -> {
            currentPage = 0;
            visibleProducts.clear();
            fetchProducts(Long.valueOf(2));
        });

        // Sự kiện tải thêm sản phẩm
        btnLoadMore.setOnClickListener(view -> loadMoreItems());

    }

    private void fetchCategories() {
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("Retrofit", "Lỗi tải danh mục: " + t.getMessage());
            }
        });
    }

    private void fetchProducts(Long categoryId) {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        apiService.getProductsByCategory(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;

                if (response.isSuccessful() && response.body() != null) {
                    allProducts = response.body();
                    visibleProducts.clear();
                    currentPage = 0;
                    loadMoreItems();
                } else {
                    Log.e("API Response", "Lỗi API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API Error", t.getMessage());
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
            }
        });
    }

    private void loadMoreItems() {
        if (isLoading) return;
        isLoading = true;

        int start = currentPage * itemPerPage;
        int end = Math.min(start + itemPerPage, allProducts.size());

        if (start < end) {
            visibleProducts.addAll(allProducts.subList(start, end));
            productAdapter.notifyDataSetChanged();
            currentPage++;
        }

        isLoading = false;
        btnLoadMore.setVisibility(currentPage * itemPerPage >= allProducts.size() ? View.GONE : View.VISIBLE);
    }

}
