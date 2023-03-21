package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
ImageView imageView;
TextView tvSolgon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.imageview);
        tvSolgon=findViewById(R.id.tvSolgon);


        Intent intent=new Intent(SplashActivity.this, SigninActivity.class);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        startActivity(intent);
        finish();

    }
},2000);

    }
}