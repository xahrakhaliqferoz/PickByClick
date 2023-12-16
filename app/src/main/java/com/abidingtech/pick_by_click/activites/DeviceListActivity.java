package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.abidingtech.pick_by_click.classes.Device;
import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeviceListActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     ArrayList<Device>list;
     DatabaseReference databaseReference;
     DeviceAdapter adapter;
     FloatingActionButton plusButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView =findViewById(R.id.recycleview);
        databaseReference=FirebaseDatabase.getInstance().getReference("Devices");
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // This method will be called when a new device is added to the database
                Device device = snapshot.getValue(Device.class);
                if (device != null) {
                    list.add(device);
                    adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // This method will be called when an existing device's data is changed in the database
                // You can handle this if needed
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // This method will be called when a device is removed from the database
                Device device = snapshot.getValue(Device.class);
                if (device != null) {
                    list.remove(device);
                    adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // This method will be called when a device changes position in the database (unlikely in your case)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any error that occurs while listening to the database changes
            }
        });
    }
}


