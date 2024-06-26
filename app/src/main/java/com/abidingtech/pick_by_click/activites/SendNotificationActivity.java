package com.abidingtech.pick_by_click.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.classes.Device;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendNotificationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    TextView ttext;


    ArrayList<Device> deviceList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_devices);

        Toolbar stoolbar = findViewById(R.id.stoolbar);
        TextView ttext = findViewById(R.id.ttext);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference("Devices")
                .child(FirebaseAuth.getInstance().getUid());
        ArrayList<Device> deviceList = new ArrayList<>();


        loadDeviceData();
    }
    private void loadDeviceData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deviceList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Device device = dataSnapshot.getValue(Device.class);
                    if (device != null) {
                        deviceList.add(device);
                    } else {
                        Log.e("SendNotificationActivity", "Device is null");
                    }
                }

                Log.d("SendNotificationActivity", "Retrieved " + deviceList.size() + " devices");

                DeviceAdapter adapter = new DeviceAdapter(SendNotificationActivity.this, deviceList, true, new DeviceAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Device device) {
                    }
                });

                recyclerView.setAdapter(adapter);

                Log.d("SendNotificationActivity", "Adapter set with " + deviceList.size() + " devices");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SendNotificationActivity", "Database Error: " + error.getMessage());
            }
        });
    }

    private void sendNotificationToDevice(Device device) {
        String notificationKey = databaseReference.child(device.getId()).child("notifications").push().getKey();

        Toast.makeText(this, "Notification sent to " + device.getName(), Toast.LENGTH_SHORT).show();
    }



}






