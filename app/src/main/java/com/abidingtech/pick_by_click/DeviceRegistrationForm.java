package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abidingtech.pick_by_click.databinding.ActivityDeviceRegistrationFormBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeviceRegistrationForm extends AppCompatActivity {
    ActivityDeviceRegistrationFormBinding binding;
    String name,id;
    FirebaseDatabase db;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_registration_form);
        binding=ActivityDeviceRegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=binding.name.getText().toString();
                id=binding.id.getText().toString();
                if(!name.isEmpty()&&!id.isEmpty())
                {
                    Devices devices=new Devices(name,id);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Devices");
                    reference.child(name).setValue(devices).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.name.setText("");
                            binding.id.setText("");
                            Toast.makeText(DeviceRegistrationForm.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}