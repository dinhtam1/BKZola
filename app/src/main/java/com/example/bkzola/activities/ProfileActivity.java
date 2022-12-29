package com.example.bkzola.activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bkzola.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    private static final int MY_REQUEST_CODE = 10;
    Button btn_SignOut , btn_Update, btn_reload;
    EditText textName , textMail;
    CircleImageView iv_avatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_SignOut = findViewById(R.id.btn_SignOut);
        btn_Update = findViewById(R.id.btn_Update);
        textName = findViewById(R.id.textName);
        textMail = findViewById(R.id.textMail);
        iv_avatar = findViewById(R.id.iv_avatar);
        showUserInformation();


        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAttract();
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });



    }

    public void onResume() {
        super.onResume();
        // Load lại dữ liệu tại đây
        showUserInformation();
    }


    private void nextActivity() {


        String email = textMail.getText().toString().trim();

        String name = textName.getText().toString().trim();

        Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
        intent.putExtra("key_email" , email);
        intent.putExtra("key_name" , name);
        startActivity(intent);

    }

    public void goToAttract()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
        startActivity(intent);
        finishAffinity();

    }



    private void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null ){
            return;
        }


        Uri imageView = user.getPhotoUrl();
        String name = user.getDisplayName();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        textName.setText(name);
        textMail.setText(email);

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (MY_REQUEST_CODE == requestCode && requestCode == Activity.RESULT_OK) {
//            textMail.setText(data.getStringExtra("key_email"));
//            textName.setText(data.getStringExtra("key_name"));
//        }
//    }


}
