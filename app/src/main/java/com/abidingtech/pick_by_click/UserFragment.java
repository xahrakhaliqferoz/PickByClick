package com.abidingtech.pick_by_click;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.AsyncListUtil;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.abidingtech.pick_by_click.databinding.FragmentUserBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.DatabaseReference;


public class UserFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final String STORAGE_PATH = "images/";
    private static final String DATABASE_PATH = "users/";
    private StorageReference storageReference;
    private String userName;
    private String userEmail;
    private ImageView imageView;


//
//    private ImageView profileImageView;
//    private DatabaseReference usersRef;
//    private FirebaseUser currentUser;
//    private String name;
//    private String email;

    TextView tvEmail;
    EditText etName;
    FloatingActionButton selectImageButton;
    Button btnSignout;

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

        // Get the currently logged-in user's name and email
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userName = auth.getCurrentUser().getDisplayName();
        userEmail = auth.getCurrentUser().getEmail();
        btnSignout = view.findViewById(R.id.btnSigout);
        btnSignout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logout")
                        .setMessage("Are u sure")
                        .setPositiveButton("Yes", (dialogInterface, i) ->
                        {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(getActivity(), SigninActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
        });
        imageView = view.findViewById(R.id.profile_image_view);
        selectImageButton = view.findViewById(R.id.select_image_button);

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

        StorageReference imageRef = storageReference.child("Users");
        imageRef.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully
                    // TODO: Handle success
                })
                .addOnFailureListener(exception -> {
                    // Handle unsuccessful image upload
                    // TODO: Handle failure
                });
    }


//
//    etName = view.findViewById(R.id.etName);
//        tvEmail = view.findViewById(R.id.tvEmail);
//        btnSignout = view.findViewById(R.id.btnSigout);
//
//        profileImageView = view.findViewById(R.id.profile_image_view);
//        selectImageButton = view.findViewById(R.id.select_image_button);
//
//
//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, REQUEST_IMAGE_PICK);
//            }
//        });
//        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        //code
//        btnSignout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Logout")
//                        .setMessage("Are u sure")
//                        .setPositiveButton("Yes", (dialogInterface, i) ->
//                        {
//                            auth = FirebaseAuth.getInstance();
//                            auth.signOut();
//                            Intent intent = new Intent(getActivity(), SigninActivity.class);
//                            startActivity(intent);
//                            getActivity().finish();
//                        })
//                        .setNegativeButton("No", (dialogInterface, i) -> {
//                        })
//                        .show();
//            }
//        });
//        return view;
//    }

//    // Method to save/update the user's profile
//    private void saveUserProfile (String name, String email){
//        if (currentUser != null) {
//            String userId = currentUser.getUid();
//            String profileImageUrl = "";
//            // TODO: Get the URL of the selected image or use a placeholder
//
//            User user = new User(userId, name, email, profileImageUrl);
//            usersRef.child(userId).setValue(user)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                // Profile saved/updated successfully
//                            } else {
//                                // Failed to save/update profile
//                            }
//                        }
//                    });
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                Uri imageUri = data.getData();
//                // Use the selected image URI as needed (e.g., display in ImageView)
//                profileImageView.setImageURI(imageUri);
//            }
//        }
//    }
//
//
//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            Uri uri=data.getData();
//            imageView.setImageURI(uri);
//        }
}



