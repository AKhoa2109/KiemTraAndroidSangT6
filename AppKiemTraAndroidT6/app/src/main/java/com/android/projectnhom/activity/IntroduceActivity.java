package com.android.projectnhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.projectnhom.APIService;
import com.android.projectnhom.PrefManager;
import com.android.projectnhom.R;
import com.android.projectnhom.entity.LoginRequest;
import com.android.projectnhom.entity.LoginResponse;
import com.android.projectnhom.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Nguyễn Hoàng Anh Khoa - 22110352*/
public class IntroduceActivity extends AppCompatActivity {
    private PrefManager prefManager;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introduce);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefManager = new PrefManager(this);

        AnhXa();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginStatusAndNavigate();
            }
        });
    }

    private void AnhXa(){
        btnStart = findViewById(R.id.btnIntroduce);
    }

    private void checkLoginStatusAndNavigate() {
        if (prefManager.isUserLogOut()) {
            startActivity(new Intent(IntroduceActivity.this, LoginActivity.class));
        } else {
            loginByShared(prefManager.getEmail(), prefManager.getPassword());
        }
        finish();
    }

    //Le Dinh Loc - 22110369
    private void loginByShared(String email, String pass){
        APIService apiService = RetrofitClient.getApiService();
        apiService.login(new LoginRequest(email, pass)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getUserId() != -1) {
                        Intent intent = new Intent( IntroduceActivity.this, MainActivity.class);
                        intent.putExtra("userId", loginResponse.getUserId());
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(IntroduceActivity.this, "Error khi login " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}