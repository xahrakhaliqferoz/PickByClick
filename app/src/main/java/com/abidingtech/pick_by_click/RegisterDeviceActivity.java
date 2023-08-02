package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterDeviceActivity extends AppCompatActivity {
    Button btnInsert, btnView;
    EditText name, id;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
        btnInsert = findViewById(R.id.btninsert);
        btnView = findViewById(R.id.btnview);
        name = findViewById(R.id.edtname);
        id = findViewById(R.id.edtid);
        databaseUsers = FirebaseDatabase.getInstance().getReference();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterDeviceActivity.this, UserList.class));
                finish();
            }
        });
    }

    private void InsertData() {
        String username = name.getText().toString();
        String userid = id.getText().toString();
        String id = databaseUsers.push().getKey();

        Device user = new Device(username, userid);
        assert id != null;
        databaseUsers.child("Devices").child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterDeviceActivity.this, "User Detail inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
//    private static final int REQUEST_CODE_DEVICE_REGISTRATION = 1;
//   TextView tvDeviceName, tvDeviceId;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register_device);
//        tvDeviceName = findViewById(R.id.textname);
//        tvDeviceId = findViewById(R.id.textid);
//
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        Object dataList=new Object();
//        RecyclerView.Adapter<MyViewHolder> adapter = new YourRecyclerViewAdapter(dataList); // Replace YourRecyclerViewAdapter with your actual adapter class
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        findViewById(R.id.floatingButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start DeviceRegistrationForm activity to register a new device
//                Intent intent = new Intent(RegisterDeviceActivity.this, DeviceRegistrationForm.class);
//                startActivity(intent);
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_DEVICE_REGISTRATION && resultCode == RESULT_OK) {
//            // Receive data from DeviceRegistrationForm activity and display it
//            String deviceName = data.getStringExtra("deviceName");
//            String deviceId = data.getStringExtra("deviceId");
//            tvDeviceName.setText(deviceName);
//            tvDeviceId.setText(deviceId);
//        }
//    }
//}
