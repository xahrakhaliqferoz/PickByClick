package com.abidingtech.pick_by_click.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.activites.RegisterDeviceActivity;
import com.abidingtech.pick_by_click.activites.SendNotificationActivity;
import com.abidingtech.pick_by_click.activites.HelpActivity;
import com.abidingtech.pick_by_click.classes.NotificationModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeFragment extends Fragment {

    TextView tvUserName;
    CardView cardHelp;
    CardView registerationCard;
    CardView SNotificationCard;
    CardView NotificationCard;
    TextView NotificationMsg;


    private FirebaseUser User;
    private DatabaseReference reference;

    private String userID;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        cardHelp = view.findViewById(R.id.HelpCard);
        registerationCard=view.findViewById(R.id.RegisterationCard);
        tvUserName = view.findViewById(R.id.tvUserName);
        SNotificationCard=view.findViewById(R.id.SNotificationCard);
        NotificationCard=view.findViewById(R.id.NotificationCard);
        NotificationMsg=view.findViewById(R.id.NotificationMsg);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationRef = database.getReference("Notifications").child(FirebaseAuth.getInstance().getUid());

        notificationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    NotificationMsg.setText(ds.child("body").getValue(String.class)+"");

                }

                        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SNotificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendNotificationActivity.class);
                startActivity(intent);

            }
        });

        cardHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
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
            String userId = currentUser.getUid();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);

                    tvUserName.setText("Hello, " + name + "!");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        return view;

    }

}