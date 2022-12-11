package com.example.bkzola.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bkzola.R;
import com.example.bkzola.databinding.ActivitySignInBinding;
import com.example.bkzola.databinding.ActivitySignUpBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    TextView textCreateNewAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textCreateNewAccount = findViewById(R.id.textCreateNewAccount);

        textCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignInActivity.this , SignUpActivity.class);
                startActivity(intent);
            }
        });

    }




}