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

public class DeviceAdapter<User> extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
    Context context;
//    private List<User> userList;
    List<User> userList = new ArrayList<>();
// Add user objects to the list...



   DeviceAdapter<User> adapter = new DeviceAdapter<>(userList);

    public DeviceAdapter(Context context, ArrayList<Device> list) {
        this.context = context;
        this.userList = (List<User>) list;
    }
    public DeviceAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.device_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(Device.getName());
       holder.id.setText(Device.getId());
    }

    public int getItemCount() {
        // Make sure deviceList is not null before accessing its size
        return userList != null ? userList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.id);

        }
    }
}
