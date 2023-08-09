package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.classes.Device;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeviceRegistrationFormActivity extends AppCompatActivity {
    CardView cardView, imageCard;
    EditText name, id;
    Button savebtn;
    ImageView cardIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_registration_form);
        cardView = findViewById(R.id.cardView);
        imageCard = findViewById(R.id.imageCard);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        cardIM = findViewById(R.id.cardIM);
        savebtn = findViewById(R.id.savebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deviceName = name.getText().toString().trim();
                String deviceId = id.getText().toString().trim();

                if (deviceName.isEmpty()) {
                    name.setError("Device name is required");
                } else if (deviceId.isEmpty()) {
                    id.setError("Device ID is required");
                } else {
                    // Both fields are filled, proceed with saving
                    DatabaseReference devicesRef = FirebaseDatabase.getInstance()
                            .getReference("Devices")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child(deviceId);

                    Device device = new Device(deviceName, deviceId);
                    devicesRef.setValue(device).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(DeviceRegistrationFormActivity.this, "Device saved successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        });
    }
}
