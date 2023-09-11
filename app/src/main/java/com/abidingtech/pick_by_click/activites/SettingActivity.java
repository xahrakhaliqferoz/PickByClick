package com.abidingtech.pick_by_click.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abidingtech.pick_by_click.NotifyUtil;
import com.abidingtech.pick_by_click.R;

public class SettingActivity extends AppCompatActivity {
TextView UserManual;
Button btnuserManual, btnsettingNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnsettingNotification=findViewById(R.id.btnsettingNotification);

        UserManual=findViewById(R.id.UserManual);
        btnuserManual=findViewById(R.id.btnuserManual);
        btnuserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, UserManualActivity.class);
                startActivity(intent);
            }
        });
        btnsettingNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifyUtil notifyUtil = new NotifyUtil();
                notifyUtil.send("NotificationTopic", "Notification Title","body");

            }
        });
    }
}