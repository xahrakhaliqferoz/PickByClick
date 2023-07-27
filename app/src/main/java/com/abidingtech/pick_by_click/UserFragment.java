package com.abidingtech.pick_by_click;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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


public class UserFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
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
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
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
        });

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

        // Get the currently logged-in user's name and email
        {
            String userId = FirebaseAuth.getInstance().getUid();

            // Get a reference to the Firebase Realtime Database
             userRef = FirebaseDatabase.getInstance()
                    .getReference(DATABASE_PATH).child(userId);

            // Listen for changes in the user's data
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve the user's name and email
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


        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

//        loadProfileImage();


        return view;
    }



    private void openImagePicker() {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(512)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(512, 512)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            uploadImage(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


    private void uploadImage(Uri uri) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            StorageReference imageRef = storageReference.child("Users/" + userId + "/" + uri.getLastPathSegment());
            UploadTask uploadTask = imageRef.putFile(uri);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image uploaded successfully
                // Get the download URL of the uploaded image
                imageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                    // Save the download URL to the database or use it as needed
                    Glide.with(this).load(downloadUri.toString()).into(imageView);
                    userRef.child("imageUrl").setValue(downloadUri.toString());
                }).addOnFailureListener(exception -> {
                    // Handle failure to get the download URL
                });
            }).addOnFailureListener(exception -> {
                // Handle unsuccessful image upload
            });
        }
    }

/*    private void loadProfileImage() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            StorageReference imageRef = storageReference.child("Users/" + userId + "/profileImage");
            imageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {

                mImageUrl = downloadUri;
                Glide.with(this).load(mImageUrl).into(imageView);
            }).addOnFailureListener(exception -> {

            });
        }
    }*/
//private void setUserNameAndEmail() {
//    if (userName != null) {
//        tvName.setText(userName);
//    }
//    if (userEmail != null) {
//        tvEmail.setText(userEmail);
//    }
//}
}

