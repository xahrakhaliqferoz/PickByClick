package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    String email,password;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.etEmail.getText().toString();
                password = binding.etPassword.getText().toString();
                auth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SigninActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SigninActivity.this, "Fail to login", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });

        binding.btnSignUpnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
            }
        });


    }
}



