package com.abidingtech.pick_by_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btnSignIn;
    Button btnSignUpNow;
    public String SHARED_PREFS = "sharedPrefs";

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        checkBox();
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(SigninActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUpNow = findViewById(R.id.btnSignUpnow);

        btnSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent1);
                finish();

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                Log.e("MainActivity", email);
                String password = etPassword.getText().toString();
                Log.e("MainActivity", password);

                if (email.length() < 1 || password.length() < 8) {
                    if (email.length() < 1) {

                        Toast.makeText(SigninActivity.this, "Email should be entered", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 8) {
                        Toast.makeText(SigninActivity.this, "Password should be greater than 8", Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    mLoadingBar.setTitle("Registration");
                    mLoadingBar.setMessage("Please wait");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SigninActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }

        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("Name", "true");
        if (check.equals("false")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


}

