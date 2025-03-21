package com.android.projectnhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projectnhom.APIService;
import com.android.projectnhom.R;
import com.android.projectnhom.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// Quảng Đại Thiện - 22110426
public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> request = new HashMap<>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);
        request.put("confirmPassword", confirmPassword);

        APIService apiService = RetrofitClient.getApiService();
        Call<String> call = apiService.register(request);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    // Chuyển sang màn hình nhập OTP
//                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
//                    startActivity(intent);
//                }
                if(response.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
