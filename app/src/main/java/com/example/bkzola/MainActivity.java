package com.example.bkzola;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bkzola.fragment.ProfileFragment;
import com.example.bkzola.tranformer.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 view_pager_2;
    private BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_pager_2 = findViewById(R.id.view_pager_2);
        bottom_navigation = findViewById(R.id.bottom_navigation);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);

        view_pager_2.setAdapter(myViewPagerAdapter);
        view_pager_2.setPageTransformer(new ZoomOutPageTransformer());

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    view_pager_2.setCurrentItem(0);

                } else if (id == R.id.bottom_search){
                    view_pager_2.setCurrentItem(1);
                } else if (id == R.id.bottom_profile) {
                    view_pager_2.setCurrentItem(2);

                }

                return true;
            }
        });

        view_pager_2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0 :
                        bottom_navigation.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1 :
                        bottom_navigation.getMenu().findItem(R.id.bottom_search).setChecked(true);
                        break;
                    case 2 :
                        bottom_navigation.getMenu().findItem(R.id.bottom_profile).setChecked(true);
                        break;

                }
            }
        });
    }
}