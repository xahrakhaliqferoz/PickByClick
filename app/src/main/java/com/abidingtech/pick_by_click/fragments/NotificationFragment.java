package com.abidingtech.pick_by_click.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abidingtech.pick_by_click.adapter.NotificationAdapter;
import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.classes.NotificationModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    TextView tvTitle, tvBody,tvTime;

    public NotificationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvBody=view.findViewById(R.id.tvBody);
        tvTime=view.findViewById(R.id.tvTime);



        databaseReference = FirebaseDatabase.getInstance().getReference("Notifications")
                .child(FirebaseAuth.getInstance().getUid());


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<NotificationModel> list = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        NotificationModel notificationModel = dataSnapshot.getValue(NotificationModel.class);
                        list.add(notificationModel);
                        Log.e("NotificationFragment", "Notification Data Retrieved: " + notificationModel.body);

                    }

                    NotificationAdapter notificationAdapter = new NotificationAdapter(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(notificationAdapter);
                } else {
                    Log.d("NotificationFragment", "No data found in Firebase");
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("NotificationFragment", "Firebase Database Error: " + error.getMessage());
            }
        });
    }

}


