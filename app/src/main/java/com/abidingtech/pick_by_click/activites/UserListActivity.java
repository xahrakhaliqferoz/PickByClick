package com.abidingtech.pick_by_click.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.abidingtech.pick_by_click.classes.Device;
import com.abidingtech.pick_by_click.adapter.MyAdapter;
import com.abidingtech.pick_by_click.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     ArrayList<Device>list;
     DatabaseReference databaseReference;
     MyAdapter adapter;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserListActivity.this, RegisterDeviceActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView =findViewById(R.id.recycleview);
        databaseReference=FirebaseDatabase.getInstance().getReference("Devices");
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Device user=dataSnapshot.getValue(Device.class);
                    list.add(user);
                }
                adapter=new MyAdapter(UserListActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
//        databaseReference = FirebaseDatabase.getInstance().getReference("users");
//        list= new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter =new MyAdapter(this,list);
//        recyclerView.setAdapter(adapter);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               for (DataSnapshot dataSnapshot:snapshot.getChildren())
//               {
//                   User user= dataSnapshot.getValue(User.class);
//                   list.add(user);
//               }
//               adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//


    }
}