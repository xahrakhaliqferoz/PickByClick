package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DeviceDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_display);
        Intent intent=getIntent();

        String name=intent.getStringExtra("Rname");
        String id=intent.getStringExtra("Rid");


        TextView nameTextView=findViewById(R.id.displayName);
        nameTextView.setText(name);


        TextView idTextView=findViewById(R.id.displayId);
        idTextView.setText(id);
    }
}