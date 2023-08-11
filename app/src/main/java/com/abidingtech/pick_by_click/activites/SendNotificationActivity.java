package com.abidingtech.pick_by_click.activites;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.classes.Device;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_devices);
        recyclerView = findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        databaseReference = FirebaseDatabase.getInstance().getReference("Devices")
                .child(FirebaseAuth.getInstance().getUid());

        DeviceAdapter adapter = new DeviceAdapter(new ArrayList<>(), new DeviceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Device device) {
                sendNotificationToDevice(device);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void sendNotificationToDevice(Device device) {
        // Here, you can update the notification information for the selected device
        // For example, you can create a "notifications" node under each device's data and add notifications there.
        String notificationKey = databaseReference.child(device.getId()).child("notifications").push().getKey();
        databaseReference.child(device.getId()).child("notifications").child(notificationKey).setValue("Your notification message");

        // Notify the user that the notification was sent
        Toast.makeText(this, "Notification sent to " + device.getName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        // Start the HomeActivity when the back button is pressed
        startActivity(new Intent(SendNotificationActivity.this, HomeActivity.class));
        finish(); // Optional: Finish this activity if you don't want to keep it in the back stack
    }


    @Override
    protected void onResume() {
        super.onResume();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Device> list = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Device user = dataSnapshot.getValue(Device.class);
                    list.add(user);
                }
                DeviceAdapter adapter = new DeviceAdapter(SendNotificationActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
