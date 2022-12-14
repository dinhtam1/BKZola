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

import com.example.bkzola.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    LinearLayout LayoutSignUp;
    Button buttonSignIn;
    EditText inputEmail, inputPassword;
    TextView txtForgotPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initUi();
        initListener();

    }



    private void initUi() {
        txtForgotPassWord = findViewById(R.id.txtForgotPassWord);
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

        txtForgotPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this , ForgotActivity.class));
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
                                finish();

                        } else {
                            Toast.makeText(SignInActivity.this, "????ng nh???p kh??ng th??nh c??ng",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


}