package com.example.bkzola.activities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bkzola.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;


public class UpdateActivity extends AppCompatActivity {

    EditText textMail, textName;
    Button btn_Save , btn_UploadPhoto;
    ProgressDialog progressDialog;
    CircleImageView ivAvatar;
    Uri imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        initUI();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null ) {
            imagePath = data.getData();
            try {
                getImageInImageView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getImageInImageView() throws IOException {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e ){
            e.printStackTrace();
        }

        ivAvatar.setImageBitmap(bitmap);
    }

    private void initUI() {
        textMail = findViewById(R.id.textMail);
        textName = findViewById(R.id.textName);
        btn_Save = findViewById(R.id.btn_Save);
        ivAvatar = findViewById(R.id.iv_avatar);
        btn_UploadPhoto = findViewById(R.id.btn_UploadPhoto);
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
//                goToAttract();

            }
        });

        btn_UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent, 1);
            }
        });

    }

    private void uploadImage() {
        progressDialog.show();


        FirebaseStorage.getInstance().getReference("images/"+UUID.randomUUID().toString()).putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
               if(task.isSuccessful()) {
                   task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                       @Override
                       public void onComplete(@NonNull Task<Uri> task) {
                           if(task.isSuccessful()){
                               updateProfilePicture(task.getResult().toString());

                           }
                       }


                   });
                   Toast.makeText(UpdateActivity.this , "Lưu ảnh thành công " , Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(UpdateActivity.this , task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

               }
                progressDialog.dismiss();
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
//                .setPhotoUri()
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

    private void updateProfilePicture(String url) {
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/profilePicture").setValue(url);
    }

//    public void goToAttract() //Cập nhật email và tên mới trong DETAIL
//    {
//        String EmailUpdate = textMail.getText().toString().trim();
//        String NameUpdate = textName.getText().toString().trim();
//        Detail.email = EmailUpdate;
//        Detail.name = NameUpdate;


//
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("key_email" ,email);
//        returnIntent.putExtra("key_name" ,name);
//        setResult(Activity.RESULT_OK , returnIntent);
//        finish();


//    }
}