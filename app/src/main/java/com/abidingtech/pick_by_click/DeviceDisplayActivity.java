package com.abidingtech.pick_by_click;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;

public class DeviceDisplayActivity extends AppCompatActivity {
  Button btnSendNoti;
    DatabaseReference databaseReference;
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

       databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
        btnSendNoti=findViewById(R.id.btnSendNoti);
        btnSendNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //es knandar device id k under alrt value tru save karwani haz

                int alertNumber = ThreadLocalRandom.current().nextInt(100, 1000);
                String deviceId=id.toString();
                DatabaseReference deviceRef=databaseReference
//                         .child(FirebaseAuth.getInstance().getUid())
                        .child(deviceId)
                        .child("alert");
                deviceRef.setValue(alertNumber);
                Toast.makeText(DeviceDisplayActivity.this, "Notification sent", Toast.LENGTH_SHORT).show();

            }
        });


    }
}