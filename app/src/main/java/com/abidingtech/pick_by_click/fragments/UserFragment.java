package com.abidingtech.pick_by_click.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.activites.EditProfileActivity;
import com.abidingtech.pick_by_click.activites.SigninActivity;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class

UserFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int EDIT_PROFILE_REQUEST_CODE = 1001;
    private static final String STORAGE_PATH = "images/";
    private static final String DATABASE_PATH = "Users";

    private StorageReference storageReference;
    private ImageView imageView;

    TextView tvEmail;
    TextView tvName;
    FloatingActionButton selectImageButton;
    Button btnLogout;
    DatabaseReference userRef;
    Button EditProfileButton;

    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        storageReference = FirebaseStorage.getInstance().getReference();

        btnLogout = view.findViewById(R.id.btnLogout);
        imageView = view.findViewById(R.id.profile_image_view);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        selectImageButton = view.findViewById(R.id.select_image_button);
        EditProfileButton=view.findViewById(R.id.EditProfileButton);


        EditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });;

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logout")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(getActivity(), SigninActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            // Do nothing
                        })
                        .show();
            }
        });

        {
            String userId = FirebaseAuth.getInstance().getUid();

             userRef = FirebaseDatabase.getInstance()
                    .getReference(DATABASE_PATH).child(userId);

        }


        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });



        return view;
    }



    private void openImagePicker() {
        ImagePicker.with(this)
                .crop()
                .compress(512)
                .maxResultSize(512, 512)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // Refresh the user data after editing profile
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            Log.e("onActivityResult: ", imageUri+"");

            uploadImage(imageUri);


        }
        else{
            Log.e("onActivityResult: ", "Result not ok");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUserData();
    }

    private void refreshUserData() {

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("name").getValue(String.class);
                String userEmail = dataSnapshot.child("email").getValue(String.class);
                String imageUrl =  dataSnapshot.child("imageUrl").getValue(String.class);

                Glide.with(getActivity()).load(imageUrl).into(imageView);

                if (userName != null) {
                    tvName.setText(userName);
                }

                if (userEmail != null) {
                    tvEmail.setText(userEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }



    private void uploadImage(Uri uri) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Log.e("uploadImage: ", uri+"");

        if (user != null) {
            String userId = user.getUid();
            StorageReference imageRef = storageReference.child("Users/" + userId + "/" + uri.getLastPathSegment());
            UploadTask uploadTask = imageRef.putFile(uri);

            uploadTask.addOnSuccessListener(taskSnapshot -> {

                imageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                    Glide.with(this).load(downloadUri.toString()).into(imageView);
                    userRef.child("imageUrl").setValue(downloadUri.toString());
                }).addOnFailureListener(exception -> {
                });
            }).addOnFailureListener(exception -> {
            });
        }

    }

}

