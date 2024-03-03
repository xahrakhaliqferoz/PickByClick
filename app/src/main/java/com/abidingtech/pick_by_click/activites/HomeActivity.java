package com.abidingtech.pick_by_click.activites;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abidingtech.pick_by_click.NotificationUtil;
import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.abidingtech.pick_by_click.fragments.HomeFragment;
import com.abidingtech.pick_by_click.fragments.NotificationFragment;
import com.abidingtech.pick_by_click.fragments.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity {
    ActivityHomectivityBinding binding;

    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homectivity);



        binding = ActivityHomectivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        replaceFragment(new HomeFragment());
        binding.bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnvHome:
                        replaceFragment(new HomeFragment());
                        return true;

                    case R.id.bnvNotification:
                        replaceFragment(new NotificationFragment());
                        return true;

                    case R.id.bnvUser:
                        replaceFragment(new UserFragment());
                        return true;
                }
                return false;
            }
        });
        Fragment homeFragment = new HomeFragment();

        getSupportFragmentManager().

                beginTransaction()
                        .

                replace(R.id.fragment_container, homeFragment)
                        .

                commit();

    }

    @Override
    public void onBackPressed() {

        if (selectedFragment instanceof HomeFragment) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        } else {
            replaceFragment(new HomeFragment());
        }
    }





    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navContainer, fragment);
        fragmentTransaction.commit();
        selectedFragment = fragment;
    }



}