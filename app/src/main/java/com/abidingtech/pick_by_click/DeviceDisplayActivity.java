package com.abidingtech.pick_by_click;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

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
                String deviceId=id.toString();
                DatabaseReference deviceRef=databaseReference
                         .child(FirebaseAuth.getInstance().getUid())
                         .child(deviceId);

                 deviceRef.setValue("true");
            }
        });


    }
}