package com.abidingtech.pick_by_click.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.activites.RegisterDeviceActivity;
import com.abidingtech.pick_by_click.activites.SendNotificationActivity;
import com.abidingtech.pick_by_click.activites.SettingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment {

    TextView tvUserName;
    CardView cardSettings;
    CardView registerationCard;
    CardView SNotificationCard;
    CardView NotificationCard;


    private FirebaseUser User;
    private DatabaseReference reference;

    private String userID;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        cardSettings = view.findViewById(R.id.settingCard);
        registerationCard=view.findViewById(R.id.RegisterationCard);
        tvUserName = view.findViewById(R.id.tvUserName);
        SNotificationCard=view.findViewById(R.id.SNotificationCard);
        NotificationCard=view.findViewById(R.id.NotificationCard);

        SNotificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendNotificationActivity.class);
                startActivity(intent);

            }
        });

        cardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        registerationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterDeviceActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Get the currently logged-in user's UID
            String userId = currentUser.getUid();

            // Get a reference to the Firebase Realtime Database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

            // Listen for changes in the user's data
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Retrieve the user's name from the database
                    String name = dataSnapshot.child("name").getValue(String.class);

                    // Update the TextView with the user's name
                    tvUserName.setText("Hello, " + name + "!");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database read error
                }
            });
        }

        return view;

    }

}