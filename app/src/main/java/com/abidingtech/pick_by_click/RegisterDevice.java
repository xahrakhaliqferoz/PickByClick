package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class
RegisterDevice extends AppCompatActivity {
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
floatingActionButton=findViewById(R.id.floatingButton);
floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //startActivity(new Intent(RegisterDevice.this, DeviceRegistrationForm.class));
        Intent intent=new Intent(RegisterDevice.this,DeviceRegistrationForm.class);
        startActivity(intent);
    }
});

    }
    
}