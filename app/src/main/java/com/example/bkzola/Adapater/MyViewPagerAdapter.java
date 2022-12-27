package com.example.bkzola.Adapater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bkzola.fragment.HomeFragment;
import com.example.bkzola.fragment.ProfileFragment;
import com.example.bkzola.fragment.SearchFragment;
import com.example.bkzola.fragment.UserFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {


    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 :
                return new UserFragment();
            case 1 :
                return new SearchFragment();
            case 2 :
                return new ProfileFragment();
            default :
                return new UserFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
