package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterDeviceActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_DEVICE_REGISTRATION = 1;
    TextView tvDeviceName, tvDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
//     recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Object dataList = new Object();
        RecyclerView.Adapter adapter = new YourRecyclerViewAdapter(dataList);
        // Replace YourRecyclerViewAdapter with your actual adapter class
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize TextViews in the layout
        tvDeviceName = findViewById(R.id.nameD);
        tvDeviceId = findViewById(R.id.idD);

        findViewById(R.id.floatingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DeviceRegistrationForm activity to register a new device
                Intent intent = new Intent(RegisterDeviceActivity.this, DeviceRegistrationForm.class);
                startActivity(intent);
            }
        });
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DEVICE_REGISTRATION && resultCode == RESULT_OK) {
            // Receive data from DeviceRegistrationForm activity and display it
            String deviceName = data.getStringExtra("deviceName");
            String deviceId = data.getStringExtra("deviceId");
            tvDeviceName.setText(deviceName);
            tvDeviceId.setText(deviceId);
        }
    }*/
}
