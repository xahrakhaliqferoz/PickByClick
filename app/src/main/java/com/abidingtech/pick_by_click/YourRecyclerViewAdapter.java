package com.abidingtech.pick_by_click;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.classes.UserNotifi;

import java.util.ArrayList;

public class YourRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolderNotifi> {
    Context context;
    ArrayList<UserNotifi>list;
    public YourRecyclerViewAdapter(Context context, ArrayList<UserNotifi> list) {
        this.context = context;
        this.list = list;
    }
    public YourRecyclerViewAdapter(Object dataList) {
    }
    @NonNull
    @Override
    public MyViewHolderNotifi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolderNotifi(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotifi holder, int position) {
    }
    @Override
    public int getItemCount() {
        return 0;
    }
}




