package com.example.bkzola.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bkzola.MainActivity;
import com.example.bkzola.R;
import com.example.bkzola.activities.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    private  View mView;
    Button btn_SignOut;
    @Nullable
    @Override



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile , container , false);
        btn_SignOut = mView.findViewById(R.id.btn_SignOut);
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAttract();
            }
        });
        return  mView;

    }
    public void goToAttract()
    {
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);

    }


}
