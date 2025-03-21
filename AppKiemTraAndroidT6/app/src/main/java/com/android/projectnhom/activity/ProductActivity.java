//Nguyễn Tuấn Thành - 22110418 /////
package com.android.projectnhom.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.projectnhom.APIService;
import com.android.projectnhom.R;
import com.android.projectnhom.adapter.ProductAdapter;
import com.android.projectnhom.entity.Product;
import com.android.projectnhom.retrofit.RetrofitClient1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductActivity extends AppCompatActivity {

    private RecyclerView rcProduct;
    private ProductAdapter productAdapter;
    private List<Product> allProducts = new ArrayList<>();
    private List<Product> visibleProducts = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private Button btnLoadMore;
    private EditText etSearch;
    private int itemPerPage = 2;
    private int currentPage = 0;
    private boolean isLoading = false;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        rcProduct = findViewById(R.id.rc_lastproduct);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        progressBar = findViewById(R.id.progressBar);
        btnLoadMore = findViewById(R.id.btn_load_more);
        etSearch = findViewById(R.id.et_search);

        rcProduct.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter(this, visibleProducts);
        rcProduct.setAdapter(productAdapter);

        apiService = RetrofitClient1.getInstance().create(APIService.class);
        loadAllProducts();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            currentPage = 0;
            visibleProducts.clear();
            loadAllProducts();
        });

        btnLoadMore.setOnClickListener(view -> loadMoreItems());

        etSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }

    private void loadAllProducts() {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        apiService.getProductsByCategory(1L).enqueue(new Callback<List<Product>>() {
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

    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.updateList(filteredList);
    }
}
