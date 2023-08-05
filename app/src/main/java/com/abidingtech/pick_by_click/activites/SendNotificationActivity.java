package com.abidingtech.pick_by_click.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.abidingtech.pick_by_click.R;

public class SendNotificationActivity extends AppCompatActivity {
TextView sendnoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        sendnoti=findViewById(R.id.sendnoti);
    }
}