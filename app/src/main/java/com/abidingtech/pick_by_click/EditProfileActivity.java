package com.abidingtech.pick_by_click;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etNewName;
    private EditText etNewEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etNewName = findViewById(R.id.etNewName);
        etNewEmail = findViewById(R.id.etNewEmail);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etNewName.getText().toString();
                String newEmail = etNewEmail.getText().toString();

                if (!newName.isEmpty() && !newEmail.isEmpty()) {
                    updateUserProfile(newName, newEmail);
                }
            }
        });
    }

    private void updateUserProfile(String newName, String newEmail) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);

            userRef.child("name").setValue(newName);
            userRef.child("email").setValue(newEmail);

            setResult(RESULT_OK);
            finish();
        }
    }
}
