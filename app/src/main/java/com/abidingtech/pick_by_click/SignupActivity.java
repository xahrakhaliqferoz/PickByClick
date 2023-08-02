package com.abidingtech.pick_by_click;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abidingtech.pick_by_click.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String userName,email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // for new account
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = binding.edtname.getText().toString();
                email = binding.edtemail.getText().toString();
                password = binding.edtpassword.getText().toString();

                if (userName.length() < 1 || email.length() < 1 || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.length() < 8) {
                    if (userName.length() < 1) {
                        Toast.makeText(SignupActivity.this, "Name required", Toast.LENGTH_SHORT).show();
                    } else if (email.length() < 1) {
                        Toast.makeText(SignupActivity.this, "email required", Toast.LENGTH_SHORT).show();
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(SignupActivity.this, "Enter valid email address", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 8) {
                        Toast.makeText(SignupActivity.this, "password should be grater than 8", Toast.LENGTH_SHORT).show();
                    }
                }
                // for register a user after validation user
                else {
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                                UserNotifi userDetail=new UserNotifi(userName,email);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference();
                                reference.child("Users").child(firebaseUser.getUid()).setValue(userDetail);
                                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignupActivity.this, "Please try again,something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });




    }
}




