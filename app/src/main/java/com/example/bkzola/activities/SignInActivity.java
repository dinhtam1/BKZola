package com.example.bkzola.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.example.bkzola.databinding.ActivitySignInBinding;
import com.example.bkzola.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    LinearLayout LayoutSignUp;
    Button buttonSignIn;
    EditText inputEmail, inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initUi();
        initListener();

    }

    private void initUi() {
        LayoutSignUp = findViewById(R.id.LayoutSignUp);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    private void initListener() {

        LayoutSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignInActivity.this , SignUpActivity.class);
                startActivity(intent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ocClickSignUp();
            }
        });

    }

    private void ocClickSignUp() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                Intent intent = new Intent(SignInActivity.this , MainActivity.class);
                                startActivity(intent);

                        } else {
                            Toast.makeText(SignInActivity.this, "Đăng nhập không thành công",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


}