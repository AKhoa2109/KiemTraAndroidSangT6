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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projectnhom.PrefManager;
import com.android.projectnhom.R;
/*Nguyễn Hoàng Anh Khoa - 22110352*/
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

    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void AnhXa(){
        btnLogin = findViewById(R.id.btn_login);
        edUsername = findViewById(R.id.edtusername);
        edPassword = findViewById(R.id.edtpassword);
        checkBoxRemember = findViewById(R.id.checkbox_remember);
    }


    /*Nguyễn Hoàng Anh Khoa - 22110352*/
    private void attemptLogin() {
        edUsername.setError(null);
        edPassword.setError (null);
        String email = edUsername.getText().toString();
        String password = edPassword.getText().toString();
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
            edUsername.setError(getString(R.string.error_invaliad_email));
            focusView = edUsername;
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
