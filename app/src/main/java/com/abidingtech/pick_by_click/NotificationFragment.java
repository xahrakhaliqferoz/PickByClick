package com.abidingtech.pick_by_click;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Order Request","Order is requested",R.drawable.a,"9:19pm"));
        items.add(new Item("Order Created","Order is Created",R.drawable.b,"10:28pm"));
        items.add(new Item("Order Stock Loaded","Order Stock is Loaded",R.drawable.e,"12:23pm"));
        items.add(new Item("Order Loading Start","Order Loading is Started",R.drawable.e,"1:12am"));
        items.add(new Item("Order Verified","Order is Verified",R.drawable.e,"2:28am"));
        items.add(new Item("Order Scheduled Successfully","Order is Successfully scheduled",R.drawable.e,"12:59am"));
        items.add(new Item("Order Approved","Order is Approved",R.drawable.e,"1:09am"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }

}



//    public NotificationFragment() {
//        // Required empty public constructor
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notification, container, false);
//    }
//
//<<<<<<< Updated upstream
//=======
//<<<<<<< HEAD
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        dataInitialize();
//        recyclerview=view.findViewById(R.id.recyclerview);
//        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview.setHasFixedSize(true);
//        MyAdapter myAdapter=new MyAdapter(getContext(),newsArrayList);
//        recyclerview.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
//    }
//
//    private void dataInitialize() {
//        newsArrayList = new ArrayList<n>();
//        newsHeading = new String[]{
//                getString(R.string.head_1),
//                getString(R.string.head_2),
//                getString(R.string.head_3),
//                getString(R.string.head_4),
//                getString(R.string.head_5),
//=======
//>>>>>>> b0a87741363f09b2e70aeeaecffa7da4269efc93
//>>>>>>> Stashed changes
//
//
//}