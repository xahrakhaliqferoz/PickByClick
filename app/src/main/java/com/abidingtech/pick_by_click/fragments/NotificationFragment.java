package com.abidingtech.pick_by_click.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abidingtech.pick_by_click.adapter.DeviceAdapter;
import com.abidingtech.pick_by_click.classes.Item;
import com.abidingtech.pick_by_click.R;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerview;
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
        recyclerview=view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        List<Item> items=new ArrayList<Item>();
        items=new ArrayList<>();


        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new DeviceAdapter(getActivity(),new ArrayList<>()));
        return view;


    }
}
