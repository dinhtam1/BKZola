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

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
    TextView textBackLogin;
    EditText inputEmail, inputPassword , inputPasswordConfirm, inputName;
    Button buttonSignUp;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        initUi();
        initListener();
    }

    private void initUi() {
        textBackLogin = findViewById(R.id.textBackLogin);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputName = findViewById(R.id.inputName);
        inputPasswordConfirm = findViewById(R.id.inputPasswordConfirm);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        progressDialog = new ProgressDialog(this);

    }

    private void initListener() {


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
    }

    private void onClickSignUp() {

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String passwordConfirm = inputPasswordConfirm.getText().toString().trim();
        if (!password.equals(passwordConfirm)) {
            Toast.makeText(SignUpActivity.this, "Mật khẩu xác nhận không trùng khớp",
                    Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            progressDialog.show();
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Đăng ký không thành công",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }

                    })
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            FirebaseUser firebaseUser = authResult.getUser();
                            firebaseUser.updateProfile(userProfileChangeRequest);
                        }
                    });;


        }

    }


}