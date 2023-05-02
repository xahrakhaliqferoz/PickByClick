package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
ActivityHomectivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new NotificationFragment());

        binding=ActivityHomectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.bnvHome:
                        transaction.replace(R.id.navContainer,new HomeFragment());
                   break;

                    case R.id.bnvNotification:
                        transaction.replace(R.id.navContainer,new NotificationFragment());
                        break;

                    case R.id.bnvUser:
                        transaction.replace(R.id.navContainer,new UserFragment());
                        break;
                }
                transaction.commit();


                return true;
            }
        });



    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framalayout,fragment);
        fragmentTransaction.commit();
    }

}