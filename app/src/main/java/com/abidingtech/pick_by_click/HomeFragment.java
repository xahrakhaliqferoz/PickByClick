package com.abidingtech.pick_by_click;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.UserManualActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


    public class HomeFragment extends Fragment {
        CardView cardSettings;
        TextView tvUserName;
        CardView registerationCard;

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

            cardSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), UserManualActivity.class);
                    startActivity(intent);
                }
            });
            registerationCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RegisterDevice.class);
                    startActivity(intent);
                }
            });

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                // Get the currently logged-in user's UID
                String userId = currentUser.getUid();

                // Get a reference to the Firebase Realtime Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

                // Listen for changes in the user's data
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Retrieve the user's name from the database
                        String userName = dataSnapshot.child("name").getValue(String.class);

                        // Update the TextView with the user's name
                        tvUserName.setText("Hello, " + userName + "!");
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
