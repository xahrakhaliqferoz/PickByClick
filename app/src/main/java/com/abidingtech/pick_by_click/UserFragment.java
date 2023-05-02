package com.abidingtech.pick_by_click;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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

import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.abidingtech.pick_by_click.databinding.FragmentUserBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;


public class UserFragment extends Fragment {
    EditText etEmail;
    EditText etPassword;
    Button btnSignout;
//    SigninActivity signinActivity;
    FirebaseAuth auth;
    FirebaseUser user;


    public UserFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);




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
                            getActivity().finish();})
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();     }
});


        return view;


    }

}
