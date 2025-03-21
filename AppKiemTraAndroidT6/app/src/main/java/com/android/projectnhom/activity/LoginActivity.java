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

import com.android.projectnhom.PrefManager;
import com.android.projectnhom.R;
import com.android.projectnhom.entity.LoginRequest;
import com.android.projectnhom.entity.LoginResponse;
import com.android.projectnhom.retrofit.ApiService;
import com.android.projectnhom.retrofit.RetrofitClient1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;
    CheckBox checkBoxRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        AnhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi API login
                LoginRequest loginRequest = new LoginRequest(email, password);
                ApiService apiService = RetrofitClient1.getApiService();
                Call<LoginResponse> call = apiService.login(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getId() != -1) {
                                // Đăng nhập thành công, chuyển sang MainActivity
                                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Đóng LoginActivity
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


        edPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == R.id.login || i == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        if(!new PrefManager(this).isUserLogOut()){
            startHomeActivity();
        }


    }

    private void AnhXa(){
        btnLogin = findViewById(R.id.btn_login);
        edUsername = findViewById(R.id.edtusername);
        edPassword = findViewById(R.id.edtpassword);
        checkBoxRemember = findViewById(R.id.checkbox_remember);
    }


    private void attemptLogin() {
        edUsername.setError(null);
        edPassword.setError (null);
        String email = edUsername.getText().toString(); String password = edPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid (password)) {
            edPassword.setError(getString(R.string.error_invaliad_password));
            focusView = edPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            edUsername.setError (getString(R.string.error_field_required));
            focusView = edUsername;
            cancel = true;
        }else if (!isEmailValid (email)) {
            edUsername.setError (getString(R.string.error_invaliad_email)); focusView = edUsername;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if (checkBoxRemember.isChecked())
                saveLoginDetails (email, password);
            startHomeActivity();
        }



    }
    private void startHomeActivity() {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }
}
//Huynh Thai Toan 22110436
