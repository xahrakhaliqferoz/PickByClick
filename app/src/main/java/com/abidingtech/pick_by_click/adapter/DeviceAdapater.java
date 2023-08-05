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
import java.util.List;

public class DeviceAdapater extends RecyclerView.Adapter<DeviceAdapater.MyViewHolder> {
    Context context;
    ArrayList<Device> deviceList;
    public DeviceAdapater(Context context, ArrayList<Device> list) {
        this.context = context;
        this.deviceList = list;
    }

    public DeviceAdapater(List<Device> deviceList) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.activity_user_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Device user =deviceList.get(position);
       holder.name.setText(Device.getName());
       holder.id.setText(Device.getId());
    }

    public int getItemCount() {
        // Make sure deviceList is not null before accessing its size
        return deviceList != null ? deviceList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.DName);
            id=itemView.findViewById(R.id.DID);

        }
    }
}
