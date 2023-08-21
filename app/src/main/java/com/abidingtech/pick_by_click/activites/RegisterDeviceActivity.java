package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.abidingtech.pick_by_click.classes.Device;
import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterDeviceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FloatingActionButton plusButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recycleview);
        plusButton = findViewById(R.id.plusButton);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterDeviceActivity.this, DeviceRegistrationFormActivity.class));
                finish();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Devices")
                .child(FirebaseAuth.getInstance().getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public void onBackPressed() {
        // Start the HomeActivity when the back button is pressed
        startActivity(new Intent(RegisterDeviceActivity.this, HomeActivity.class));
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
                DeviceAdapter adapter = new DeviceAdapter(RegisterDeviceActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
