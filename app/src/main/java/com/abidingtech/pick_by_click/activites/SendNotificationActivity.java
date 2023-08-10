package com.abidingtech.pick_by_click.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.abidingtech.pick_by_click.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SendNotificationActivity extends AppCompatActivity {

    TextView sendnoti;
    ListView deviceListView;
    List<String> deviceNames;
    List<String> deviceIds;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        sendnoti = findViewById(R.id.sendnoti);
        deviceListView = findViewById(R.id.deviceListView);
        deviceNames = new ArrayList<>();
        deviceIds = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceNames);
        deviceListView.setAdapter(adapter);

        loadRegisteredDevices();

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected device ID and proceed to send notification
                String selectedDeviceId = deviceIds.get(position);
                // TODO: Implement sending notification to selectedDeviceId
            }
        });
    }

    private void loadRegisteredDevices() {
        String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference devicesRef = FirebaseDatabase.getInstance().getReference("Devices").child(userId);

        devicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot deviceSnapshot : dataSnapshot.getChildren()) {
                    String deviceId = deviceSnapshot.getKey();
                    String deviceName = deviceSnapshot.child("deviceName").getValue(String.class);

                    deviceIds.add(deviceId);
                    deviceNames.add(deviceName);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
