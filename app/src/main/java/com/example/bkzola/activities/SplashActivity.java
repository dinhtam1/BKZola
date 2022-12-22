package com.example.bkzola.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();

            }
        }, 2000);
    }

    private void nextActivity() {
        Intent intent = new Intent(this , SignInActivity.class);
        startActivity(intent);
        finish();
    }

}