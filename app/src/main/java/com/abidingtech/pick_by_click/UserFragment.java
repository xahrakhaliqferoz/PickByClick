package com.abidingtech.pick_by_click;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.abidingtech.pick_by_click.databinding.FragmentUserBinding;
<<<<<<< Updated upstream
import com.github.dhaval2404.imagepicker.ImagePicker;
=======
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
>>>>>>> Stashed changes
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;


public class UserFragment extends Fragment {
<<<<<<< Updated upstream
    EditText editText;
    TextView textView;
    ImageView imageView;
    FloatingActionButton button;

=======


    EditText etEmail;
    EditText etPassword;
>>>>>>> Stashed changes
    Button btnSignout;
    //    SigninActivity signinActivity;
    FirebaseAuth auth;
    FirebaseUser user;
<<<<<<< Updated upstream
=======
    private ObjectInputStream.GetField Picasso;


>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        imageView=view.findViewById(R.id.imageView);
        button=view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UserFragment.this)
                        .galleryOnly()
                        .cropSquare()
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=data.getData();
        imageView.setImageURI(uri);
    }
}

//            btnSignout.setOnClickListener(new View.OnClickListener() {
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
//                           getActivity().finish();})
//                      .setNegativeButton("No", (dialogInterface, i) -> {
//                        })
//                        .show();     }
//});
//return view;
//   }
////   @Override
//    public void onActivityResult(int requestCode, int resultCode), @NonNull Intent data){
//
//        super.onActivityResult(requestCode,resultCode,data);
//
//}
=======



            btnSignout = view.findViewById(R.id.btnSignout);


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

    }



>>>>>>> Stashed changes
