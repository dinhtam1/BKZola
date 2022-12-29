package com.example.bkzola.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bkzola.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    Button btn_send_email;
    EditText inputEmail;
    TextView txt_backLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        initUi();
        initListener();
    }

    private void initUi() {
        btn_send_email = findViewById(R.id.btn_send_email);
        inputEmail = findViewById(R.id.inputEmail);
        txt_backLogin = findViewById(R.id.txt_backLogin);
        progressDialog = new ProgressDialog(this);
    }

    private void initListener() {
        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickForgotPassWord();
            }


        });

        txt_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotActivity.this,SignInActivity.class));
            }
        });
    }

    private void onClickForgotPassWord() {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = inputEmail.getText().toString().trim();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotActivity.this,"Hãy kiểm tra email để tạo mật khẩu mới" , Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(ForgotActivity.this,"Email không tồn tại trên hệ thống" , Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}