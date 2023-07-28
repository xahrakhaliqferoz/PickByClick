package com.abidingtech.pick_by_click;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DeviceRegistrationForm extends AppCompatActivity {

    EditText name;
    EditText id;
    CardView cardView;
    CardView secondCard;
    Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_registration_form);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        cardView=findViewById(R.id.cardView);
        secondCard=findViewById(R.id.secondCard);
        saveButton=findViewById(R.id.saveButton);

    }
}