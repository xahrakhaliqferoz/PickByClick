package com.abidingtech.pick_by_click;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DeviceSelectionActivity extends AppCompatActivity {
   Button Device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);
        Device=findViewById(R.id.DeviceSelection);


    }
}