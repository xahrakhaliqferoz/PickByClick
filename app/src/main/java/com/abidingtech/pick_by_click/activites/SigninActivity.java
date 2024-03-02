package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    String email, password;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.etEmail.getText().toString().trim();
                password = binding.etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    binding.etEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    binding.etPassword.setError("Password is required");
                    return;
                }
                // ...

                auth.signInWithEmailAndPassword(email, password)
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
                                if (e.getMessage() != null) {
                                    if (e.getMessage().contains("The password is invalid")) {
                                        // Handle invalid password error
                                        binding.etPassword.setError("Invalid password");
                                        Toast.makeText(SigninActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                    } else if (e.getMessage().contains("There is no user record corresponding to this identifier")) {
                                        // Handle email not found error
                                        binding.etEmail.setError("Email not registered");
                                        Toast.makeText(SigninActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Handle other login errors
                                        Toast.makeText(SigninActivity.this, "Failed to login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Handle unexpected errors
                                    Toast.makeText(SigninActivity.this, "Failed to login: An unexpected error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

// ...










                // Now proceed with authentication
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(SigninActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(SigninActivity.this, HomeActivity.class));
//                                finish();
//                            }
//                        })
                        // ...

//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                if (e.getMessage() != null && e.getMessage().contains("The password is invalid")) {
//                                    binding.etPassword.setError("Invalid password");
//                                } else {
//                                    Toast.makeText(SigninActivity.this, "Failed to login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

// ...

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
