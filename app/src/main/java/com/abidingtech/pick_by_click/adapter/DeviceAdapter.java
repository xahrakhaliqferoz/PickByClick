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

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
    private Context context;
    private List<Device> deviceList = new ArrayList<>();

    // Define the interface for item click events
    public interface OnItemClickListener {
        void onItemClick(Device device);
    }

    private OnItemClickListener onItemClickListener;

    public DeviceAdapter(ArrayList<Device> deviceList, OnItemClickListener listener) {
        this.deviceList = deviceList;
        this.onItemClickListener = listener;
    }

    public DeviceAdapter(Context context, List<Device> list) {
        this.context = context;
        this.deviceList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.device_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Device device = deviceList.get(position);
        holder.name.setText(device.getName());
        holder.id.setText(device.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(device);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        // Make sure deviceList is not null before accessing its size
        return deviceList != null ? deviceList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.DName);
            id = itemView.findViewById(R.id.DID);
        }
    }
}
