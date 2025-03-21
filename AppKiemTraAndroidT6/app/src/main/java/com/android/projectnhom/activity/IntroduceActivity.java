package com.android.projectnhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.projectnhom.PrefManager;
import com.android.projectnhom.R;

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

            startActivity(new Intent(this, LoginActivity.class));
        } else {

            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}