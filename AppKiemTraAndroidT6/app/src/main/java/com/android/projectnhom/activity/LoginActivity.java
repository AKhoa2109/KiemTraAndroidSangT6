package com.android.projectnhom.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projectnhom.R;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        AnhXa();

    }

    private void AnhXa(){
        btnLogin = findViewById(R.id.btn_login);
        edUsername = findViewById(R.id.textusername);
        edPassword = findViewById(R.id.text_password);
    }
}
