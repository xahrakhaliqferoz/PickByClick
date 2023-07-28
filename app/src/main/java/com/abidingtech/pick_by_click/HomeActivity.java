package com.abidingtech.pick_by_click;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity {
    ActivityHomectivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homectivity);



        FirebaseMessaging.getInstance().subscribeToTopic("broadcast")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

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
        // Example: Load your UserFragment
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
        // Get the currently displayed fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof HomeFragment) {
            // If the current fragment is UserFragment, show an AlertDialog to confirm exit
            new AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Close the app
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing and resume the app
                        }
                    })
                    .show();
        } else {
            // If the current fragment is not UserFragment, handle the back press normally
            super.onBackPressed();
        }
    }





    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navContainer, fragment);
        fragmentTransaction.commit();
    }

}