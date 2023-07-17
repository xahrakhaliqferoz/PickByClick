package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText edtname;
    EditText edtemail;
    EditText edtpassword;
    Button btnSignUp;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(SignupActivity.this);

        edtname = findViewById(R.id.edtname);
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString().trim();
                Log.e("MainActivity", email);
                String password = edtpassword.getText().toString();
                Log.e("MainActivity", password);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignupActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(SignupActivity.this, "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
                } else {
                    signUpWithEmailAndPassword(email, password);
                }
            }
        });
    }

    private void signUpWithEmailAndPassword(String email, String password) {
        mLoadingBar.setTitle("Registration");
        mLoadingBar.setMessage("Please wait");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mLoadingBar.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
