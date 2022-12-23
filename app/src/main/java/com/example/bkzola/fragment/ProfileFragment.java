package com.example.bkzola.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.example.bkzola.activities.SignInActivity;
import com.example.bkzola.activities.UpdateActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    private  View mView;
    Button btn_SignOut , btn_Update;
    EditText textName , textMail;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile , container , false);
        btn_SignOut = mView.findViewById(R.id.btn_SignOut);
        btn_Update = mView.findViewById(R.id.btn_Update);
        textName = mView.findViewById(R.id.textName);
        textMail = mView.findViewById(R.id.textMail);
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAttract();
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                startActivity(intent);
            }
        });
        showUserInformation();
        return  mView;

    }
    public void goToAttract()
    {
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);

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


}
