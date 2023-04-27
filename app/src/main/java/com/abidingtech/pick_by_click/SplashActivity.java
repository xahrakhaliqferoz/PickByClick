package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvSolgon;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageview);
        tvSolgon = findViewById(R.id.tvSolgon);
        mAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Intent intent = new Intent(SplashActivity.this, SigninActivity.class);
                    startActivity(intent);
<<<<<<< HEAD
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
=======
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
>>>>>>> fbe23c87e39e645878149a88e77440707dd1c6f2
                    startActivity(intent);
                }
                finish();

            }
        }, 2000);

    }
}