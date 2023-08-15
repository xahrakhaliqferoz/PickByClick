package com.abidingtech.pick_by_click;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.google.firebase.messaging.FirebaseMessaging;

public class DeviceDisplayActivity extends AppCompatActivity {
  Button deviceName;
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

        deviceName=findViewById(R.id.DeviceSelection);
        deviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button_Click","True");


                FirebaseMessaging.getInstance().subscribeToTopic("broadcast")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = "True";
                                if (!task.isSuccessful()) {
                                    msg = "Subscribe failed";
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(DeviceDisplayActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }
}