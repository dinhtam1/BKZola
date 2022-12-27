package com.example.bkzola.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.example.bkzola.fragment.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class UpdateActivity extends AppCompatActivity {
    EditText textMail, textName;
    Button btn_Save;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        initUI();
        initListener();
    }

    private void initUI() {
        textMail = findViewById(R.id.textMail);
        textName = findViewById(R.id.textName);
        btn_Save = findViewById(R.id.btn_Save);
        progressDialog = new ProgressDialog(this);
    }

    private void initListener() {
        textMail.setText(getIntent().getStringExtra("key_email"));
//        textMail.setText(Detail.email);
        textName.setText(getIntent().getStringExtra("key_name"));
//        textName.setText(Detail.name);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
                goToAttract();

            }
        });
    }



    private void Update() {



        String email = textMail.getText().toString().trim();
        String name = textName.getText().toString().trim();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        progressDialog.show();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(UpdateActivity.this, "Thay đổi tên thành công",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
    public void goToAttract() //Cập nhật email và tên mới trong DETAIL
    {
//        String EmailUpdate = textMail.getText().toString().trim();
//        String NameUpdate = textName.getText().toString().trim();
//        Detail.email = EmailUpdate;
//        Detail.name = NameUpdate;



//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("key_email" ,email);
//        returnIntent.putExtra("key_name" ,name);
//        setResult(Activity.RESULT_OK , returnIntent);
//        finish();


    }
}