package com.abidingtech.pick_by_click;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;

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
    private static final int REQUEST_IMAGE_PICK = 1;
    private ImageView profileImageView;
    private DatabaseReference usersRef;
    private FirebaseUser currentUser;
    private String name;
    private String email;

    TextView tvEmail;
    EditText etName;
    FloatingActionButton selectImageButton;
    Button btnSignout;

//    SigninActivity signinActivity;

    FirebaseAuth auth;

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
        etName = view.findViewById(R.id.etName);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnSignout = view.findViewById(R.id.btnSigout);

        profileImageView = view.findViewById(R.id.profile_image_view);
        selectImageButton = view.findViewById(R.id.select_image_button);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        });
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //code
        btnSignout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logout")
                        .setMessage("Are u sure")
                        .setPositiveButton("Yes", (dialogInterface, i) ->
                        {
                            auth = FirebaseAuth.getInstance();
                            auth.signOut();
                            Intent intent = new Intent(getActivity(), SigninActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
        });
        return view;
    }

    // Method to save/update the user's profile
    private void saveUserProfile (String name, String email){
        if (currentUser != null) {
            String userId = currentUser.getUid();
            String profileImageUrl = "";
            // TODO: Get the URL of the selected image or use a placeholder

            User user = new User(userId, name, email, profileImageUrl);
            usersRef.child(userId).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Profile saved/updated successfully
                            } else {
                                // Failed to save/update profile
                            }
                        }
                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                // Use the selected image URI as needed (e.g., display in ImageView)
                profileImageView.setImageURI(imageUri);
            }
        }
    }


//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            Uri uri=data.getData();
//            imageView.setImageURI(uri);
//        }
}



