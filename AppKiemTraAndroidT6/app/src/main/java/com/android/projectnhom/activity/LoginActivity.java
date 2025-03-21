//Huynh Thai Toan 22110436
package com.android.projectnhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projectnhom.APIService;
import com.android.projectnhom.PrefManager;
import com.android.projectnhom.R;
import com.android.projectnhom.entity.LoginRequest;
import com.android.projectnhom.entity.LoginResponse;
import com.android.projectnhom.retrofit.RetrofitClient;
/*Nguyễn Hoàng Anh Khoa - 22110352*/
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;
    CheckBox checkBoxRemember;
    TextView tvRegister;

    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        prefManager = new PrefManager(this);
        AnhXa();

        if (!prefManager.isUserLogOut()) {
            startHomeActivity();
        }

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //Huynh Thai Toan 22110436
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                attemptLogin();
                //Huynh Thai Toan 22110436
                // Gọi API login
                LoginRequest loginRequest = new LoginRequest(email, password);
                APIService apiService = RetrofitClient.getApiService();
                Call<LoginResponse> call = apiService.login(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getId() != -1) {
                                // Đăng nhập thành công, chuyển sang MainActivity
                                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                // Nếu người dùng chọn "Remember password"
                                if (checkBoxRemember.isChecked()) {
                                    prefManager.saveLoginDetails(email, password);
                                }
                                startHomeActivity();
                            } else {
                                // Đăng nhập thất bại (email/password sai, tài khoản không active)
                                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void AnhXa(){
        btnLogin = findViewById(R.id.btn_login);
        edUsername = findViewById(R.id.edtusername);
        edPassword = findViewById(R.id.edtpassword);
        checkBoxRemember = findViewById(R.id.checkbox_remember);
        tvRegister = findViewById((R.id.textview_signup));
    }


    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void attemptLogin() {
        edUsername.setError(null);
        edPassword.setError (null);
        String email = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edPassword.setError(getString(R.string.error_invaliad_password));
            focusView = edPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            edUsername.setError(getString(R.string.error_field_required));
            focusView = edUsername;
            cancel = true;
        }else if (!isEmailValid (email)) {
            edUsername.setError(getString(R.string.error_invaliad_email));
            focusView = edUsername;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }

    }
    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void startHomeActivity() {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }
}

