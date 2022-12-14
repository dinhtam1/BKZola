package com.example.bkzola.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bkzola.Adapter.UsersAdapter;
import com.example.bkzola.R;
import com.example.bkzola.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<User> users;
    private UsersAdapter usersAdapter;
    UsersAdapter.OnUserClickListener onUserClickListener;
    ImageView btnProfile;
    TextView tvDisplayName;
    ImageButton btn_Search;
    SwipeRefreshLayout swipeRefreshLayout;
    String myImageUrl;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                startActivity(new Intent(MainActivity.this , MesssageActivity.class)
                        .putExtra("username_of_roommate" , users.get(position).getUsername())
                        .putExtra("email_of_roommate" , users.get(position).getEmail())
                        .putExtra("img_of_roommate", users.get(position).getProfilePicture())
                        .putExtra("my_img" , myImageUrl)
                );

            }
        };

        getUsers();



        initUi();

        showUserInformation();
        initListener();

    }

    public void getUsers(){
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    users.add(dataSnapshot.getValue(User.class));
                }

                usersAdapter = new UsersAdapter(users,MainActivity.this,onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(usersAdapter);
                recyclerView.setVisibility(View.VISIBLE);

                for(User user:users) {
                    if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        myImageUrl = user.getProfilePicture();
                        return;
                    }
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        showUserInformation();
    }

    private void initUi() {
        btn_Search = findViewById(R.id.btn_Search);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        btnProfile = findViewById(R.id.btn_profile);
        tvDisplayName = findViewById(R.id.tv_DisplayName);
    }

    private void initListener() {

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , ProfileActivity.class)

                        .putExtra("my_img" , myImageUrl));


            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SearchActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null ){
            return;
        }
        String name = user.getDisplayName();
//        String email = user.getEmail();
//        String phoneNumber = user.getPhoneNumber();
        tvDisplayName.setText(name);
//        textMail.setText(email);
    }
}