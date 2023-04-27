package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bnView=findViewById(R.id.bnView);

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnvHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.navContainer, new HomeFragment()).commit();
                        break;

                    case R.id.bnvNotification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.navContainer, new NotificationFragment()).commit();
                        break;

                    case R.id.bnvUser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.navContainer, new UserFragment()).commit();
                        break;
                }

                return true;
            }
        });

    }

}