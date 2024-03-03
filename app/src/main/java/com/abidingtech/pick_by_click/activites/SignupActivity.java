package com.abidingtech.pick_by_click.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abidingtech.pick_by_click.classes.User;
import com.abidingtech.pick_by_click.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String userName,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        binding.edtemail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        binding.edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = binding.edtname.getText().toString();
                email = binding.edtemail.getText().toString();
                password = binding.edtpassword.getText().toString();


                if (!isValidUserName(userName) || email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.length() < 8) {
                    if (!isValidUserName(userName)) {
                        Toast.makeText(SignupActivity.this, "Invalid name. Only alphabetic characters are allowed.", Toast.LENGTH_SHORT).show();
                    } else if (email.trim().isEmpty()) {
                        Toast.makeText(SignupActivity.this, "Email required", Toast.LENGTH_SHORT).show();
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(SignupActivity.this, "Enter valid email address", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 8) {
                        Toast.makeText(SignupActivity.this, "Password should be greater than 8 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                                User userDetail=new User(userName,email);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference();
                                reference.child("Users").child(firebaseUser.getUid()).setValue(userDetail);
                                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                finish();
                            }
                            else {
                                if (task.getException() != null && task.getException().getMessage() != null) {
                                    String errorMessage = task.getException().getMessage();
                                    if (errorMessage.contains("email address is already in use")) {
                                        // Handle the case where the email is already in use
                                        Toast.makeText(SignupActivity.this, "Email is already in use", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignupActivity.this, "Please try again, something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
    private boolean isValidUserName(String userName) {
        String regex = "^[a-zA-Z]+$";
        return Pattern.matches(regex, userName);
    }
}




