package com.abidingtech.pick_by_click.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.classes.Device;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView .Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Device> list;
    public MyAdapter(Context context,ArrayList<Device> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Device user =list.get(position);
       holder.name.setText(user.getName());
       holder.id.setText(user.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textname);
            id=itemView.findViewById(R.id.textid);

        }
    }
}
