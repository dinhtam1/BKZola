package com.example.bkzola.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.example.bkzola.activities.Detail;
import com.example.bkzola.activities.SignInActivity;
import com.example.bkzola.activities.UpdateActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {


    private static final int MY_REQUEST_CODE = 10;
    private  View mView;
    Button btn_SignOut , btn_Update, btn_reload;
    EditText textName , textMail;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile , container , false);
        btn_SignOut = mView.findViewById(R.id.btn_SignOut);
        btn_Update = mView.findViewById(R.id.btn_Update);
        textName = mView.findViewById(R.id.textName);
        textMail = mView.findViewById(R.id.textMail);
        btn_reload = mView.findViewById(R.id.btn_reload);
        showUserInformation();

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserInformation();
            }
        });
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


        return  mView;

    }

    public void onResume() {
        super.onResume();
        // Load lại dữ liệu tại đây
        showUserInformation();
    }



    private void nextActivity() {


        String email = textMail.getText().toString().trim();
//        Detail.email = email;
        String name = textName.getText().toString().trim();
//        Detail.name = name;
        Intent intent = new Intent(getActivity(), UpdateActivity.class);
        intent.putExtra("key_email" , email);
        intent.putExtra("key_name" , name);
        startActivity(intent);

    }

    public void goToAttract()
    {
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showUserInformation();
    }

    private void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null ){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        textName.setText(name);
        textMail.setText(email);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MY_REQUEST_CODE == requestCode && requestCode == Activity.RESULT_OK) {
            textMail.setText(data.getStringExtra("key_email"));
            textName.setText(data.getStringExtra("key_name"));
        }
    }


}
