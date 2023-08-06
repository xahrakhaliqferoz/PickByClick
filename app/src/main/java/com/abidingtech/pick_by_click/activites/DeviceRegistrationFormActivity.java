
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeviceRegistrationFormActivity extends AppCompatActivity {
CardView cardView,imageCard;
EditText name,id;
Button savebtn;
ImageView cardIM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_registration_form);
        cardView=findViewById(R.id.cardView);
        imageCard=findViewById(R.id.imageCard);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        cardIM=findViewById(R.id.cardIM);
        savebtn=findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceRegistrationFormActivity.this, RegisterDeviceActivity.class));
                finish();
                String deviceName = name.getText().toString().trim();
                String deviceId = id.getText().toString().trim();

                // Check if the fields are not empty
                if (!deviceName.isEmpty() && !deviceId.isEmpty()) {
                    // Get a reference to the Firebase Realtime Database and the "Devices" node
                    DatabaseReference devicesRef = FirebaseDatabase.getInstance().getReference("Devices");

                    // Create a new unique key for the device entry
                    String deviceKey = devicesRef.push().getKey();

                    // Create a new Device object with the entered data
                    Device device = new Device(deviceName, deviceId);

                    // Store the device data under the unique key in the "Devices" node
                    devicesRef.child(deviceKey).setValue(device);

                    // Show a success message or perform any other actions you'd like after saving
                    Toast.makeText(DeviceRegistrationFormActivity.this, "Device saved successfully!", Toast.LENGTH_SHORT).show();

                    // Pass the data to RegisterDeviceActivity
                    Intent intent = new Intent(DeviceRegistrationFormActivity.this, RegisterDeviceActivity.class);
                    intent.putExtra("deviceName", deviceName);
                    intent.putExtra("deviceId", deviceId);
                    startActivity(intent);
                } else {
                    // Show an error message if any of the fields are empty
                    Toast.makeText(DeviceRegistrationFormActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}