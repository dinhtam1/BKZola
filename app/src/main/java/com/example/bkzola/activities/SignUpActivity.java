package com.example.bkzola.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bkzola.R;

public class SignUpActivity extends AppCompatActivity {
    TextView textBackLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textBackLogin = findViewById(R.id.textBackLogin);

        textBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignUpActivity.this , SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}