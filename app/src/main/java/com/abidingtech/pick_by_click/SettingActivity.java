package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
TextView UserManual;
Button btnuserManual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        UserManual=findViewById(R.id.UserManual);
        btnuserManual=findViewById(R.id.btnuserManual);
        btnuserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, UserManualActivity.class);
                startActivity(intent);

            }
        });
    }
}