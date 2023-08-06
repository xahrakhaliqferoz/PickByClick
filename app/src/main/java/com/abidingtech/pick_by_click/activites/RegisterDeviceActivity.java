package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abidingtech.pick_by_click.classes.Device;
import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterDeviceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Device>list;
    DatabaseReference databaseReference;
    DeviceAdapter adapter;
    FloatingActionButton plusButton;
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(RegisterDeviceActivity.this, RegisterDeviceActivity.class));
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView =findViewById(R.id.recycleview);
        plusButton=findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterDeviceActivity.this, DeviceRegistrationFormActivity.class));
                finish();
            }
        });
        databaseReference=FirebaseDatabase.getInstance().getReference("Device");
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Device user=dataSnapshot.child("Device").getValue(Device.class);
                    list.add(user);
                }
                adapter=new DeviceAdapter(RegisterDeviceActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
//        databaseReference = FirebaseDatabase.getInstance().getReference("users");
//        list= new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter =new MyAdapter(this,list);
//        recyclerView.setAdapter(adapter);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               for (DataSnapshot dataSnapshot:snapshot.getChildren())
//               {
//                   User user= dataSnapshot.getValue(User.class);
//                   list.add(user);
//               }
//               adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//


    }
}
//package com.abidingtech.pick_by_click.activites;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.abidingtech.pick_by_click.classes.Device;
//import com.abidingtech.pick_by_click.adapter.DeviceAdapater;
//import com.abidingtech.pick_by_click.R;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RegisterDeviceActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private DeviceAdapater adapter;
//    private List<Device> deviceList;
//FloatingActionButton plusButton;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register_device);
//
//        // Initialize views
//        recyclerView = findViewById(R.id.recycleview);
//        plusButton=findViewById(R.id.plusButton);
//        plusButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterDeviceActivity.this, DeviceRegistrationFormActivity.class));
//                finish();
//            }
//        });
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        deviceList = new ArrayList<>();
//        adapter = new DeviceAdapater(deviceList);
//        recyclerView.setAdapter(adapter);
//
//        // Retrieve the list of devices from the Realtime Database
//        DatabaseReference devicesRef = FirebaseDatabase.getInstance().getReference("Devices");
//        devicesRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                deviceList.clear();
//                for (DataSnapshot deviceSnapshot : dataSnapshot.getChildren()) {
//                    Device device = deviceSnapshot.getValue(Device.class);
//                    deviceList.add(device);
//                }
//                if (adapter != null) {
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle database read error if necessary
//                Toast.makeText(RegisterDeviceActivity.this, "Failed to retrieve devices.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
////
////        databaseReference = FirebaseDatabase.getInstance().getReference("users");
////        list= new ArrayList<>();
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        adapter =new MyAdapter(this,list);
////        recyclerView.setAdapter(adapter);
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @SuppressLint("NotifyDataSetChanged")
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////               for (DataSnapshot dataSnapshot:snapshot.getChildren())
////               {
////                   User user= dataSnapshot.getValue(User.class);
////                   list.add(user);
////               }
////               adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////
//
//
//
//
////package com.abidingtech.pick_by_click.activites;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Toast;
////
////import com.abidingtech.pick_by_click.classes.Device;
////import com.abidingtech.pick_by_click.R;
////import com.google.android.gms.tasks.OnCompleteListener;
////import com.google.android.gms.tasks.Task;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////
////public class RegisterDeviceActivity extends AppCompatActivity {
////    Button btnInsert, btnView;
////    EditText name, id;
////    DatabaseReference databaseUsers;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_register_device);
////        btnInsert = findViewById(R.id.btninsert);
////        btnView = findViewById(R.id.btnview);
////        name = findViewById(R.id.edtname);
////        id = findViewById(R.id.edtid);
////        databaseUsers = FirebaseDatabase.getInstance().getReference();
////
////        btnInsert.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                InsertData();
////            }
////        });
////        btnView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(RegisterDeviceActivity.this, UserListActivity.class));
////                finish();
////            }
////        });
////    }
////
////    private void InsertData() {
////        String username = name.getText().toString();
////        String userid = id.getText().toString();
////        String id = databaseUsers.push().getKey();
////
////        Device user = new Device(username, userid);
////        assert id != null;
////        databaseUsers.child("Devices").child(id).setValue(user)
////                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
////                        if (task.isSuccessful()) {
////                            Toast.makeText(RegisterDeviceActivity.this, "User Detail inserted", Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                });
////
////    }
////}
//////    private static final int REQUEST_CODE_DEVICE_REGISTRATION = 1;
//////   TextView tvDeviceName, tvDeviceId;
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_register_device);
//////        tvDeviceName = findViewById(R.id.textname);
//////        tvDeviceId = findViewById(R.id.textid);
//////
//////
//////        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//////        Object dataList=new Object();
//////        RecyclerView.Adapter<MyViewHolder> adapter = new YourRecyclerViewAdapter(dataList); // Replace YourRecyclerViewAdapter with your actual adapter class
//////        recyclerView.setAdapter(adapter);
//////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//////
//////        findViewById(R.id.floatingButton).setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Start DeviceRegistrationForm activity to register a new device
//////                Intent intent = new Intent(RegisterDeviceActivity.this, DeviceRegistrationForm.class);
//////                startActivity(intent);
//////            }
//////        });
//////    }
//////    @Override
//////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//////        super.onActivityResult(requestCode, resultCode, data);
//////        if (requestCode == REQUEST_CODE_DEVICE_REGISTRATION && resultCode == RESULT_OK) {
//////            // Receive data from DeviceRegistrationForm activity and display it
//////            String deviceName = data.getStringExtra("deviceName");
//////            String deviceId = data.getStringExtra("deviceId");
//////            tvDeviceName.setText(deviceName);
//////            tvDeviceId.setText(deviceId);
//////        }
//////    }
//////}
