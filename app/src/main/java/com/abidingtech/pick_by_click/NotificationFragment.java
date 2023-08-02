package com.abidingtech.pick_by_click;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Item> items;
    public NotificationFragment() {
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
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        List<Item> items=new ArrayList<Item>();
        items=new ArrayList<>();
        items.add(new Item("Order Request","Order is requested",R.drawable.a,"9:19pm"));
        items.add(new Item("Order Created","Order is Created",R.drawable.a,"10:28pm"));
        items.add(new Item("Order Stock Loaded","Order Stock is Loaded",R.drawable.a,"12:23pm"));
        items.add(new Item("Order Loading Start","Order Loading is Started",R.drawable.a,"1:12am"));
        items.add(new Item("Order Verified","Order is Verified",R.drawable.a,"2:28am"));
        items.add(new Item("Order Scheduled Successfully","Order is Successfully scheduled",R.drawable.a,"12:59am"));
        items.add(new Item("Order Approved","Order is Approved",R.drawable.a,"1:09am"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterNotifi(getActivity(),new ArrayList<>()));
        return view;


    }
}
