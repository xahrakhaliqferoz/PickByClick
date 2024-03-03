package com.abidingtech.pick_by_click.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abidingtech.pick_by_click.R;

public class HelpActivity extends AppCompatActivity {
TextView UserManual,ContactUs;
Button btnuserManual , btncontactus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        UserManual=findViewById(R.id.UserManual);
        ContactUs = findViewById(R.id.ContactUs);

        btnuserManual=findViewById(R.id.btnuserManual);
        btnuserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, UserManualActivity.class);
                startActivity(intent);
            }
        });

        btncontactus=findViewById(R.id.btncontactus);
        btncontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

    }
}