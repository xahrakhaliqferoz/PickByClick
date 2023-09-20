package com.abidingtech.pick_by_click.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abidingtech.pick_by_click.NotificationAdapter;
import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.classes.NotificationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<NotificationModel> notificationModelArrayList;
    DatabaseReference databaseReference;
    NotificationAdapter notificationAdapter;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationModelArrayList = new ArrayList<>();

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificationModelArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NotificationModel notification = snapshot.getValue(NotificationModel.class);
                    notificationModelArrayList.add(notification);
                }

                NotificationAdapter adapter = new NotificationAdapter(notificationModelArrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }
}
