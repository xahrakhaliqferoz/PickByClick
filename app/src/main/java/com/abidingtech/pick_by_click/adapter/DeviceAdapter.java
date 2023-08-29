package com.abidingtech.pick_by_click.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.activites.DeviceDisplayActivity;
import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.activites.SendNotificationActivity;
import com.abidingtech.pick_by_click.classes.Device;

import java.util.ArrayList;
import java.util.List;
public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
//    private Context context;
    private List<Device> deviceList = new ArrayList<>();
    private boolean isClickable; // Flag

    public DeviceAdapter(SendNotificationActivity sendNotificationActivity, ArrayList<Device> list) {

    }

    public interface OnItemClickListener {
        void onItemClick(Device device);
    }
    private OnItemClickListener onItemClickListener;

    public DeviceAdapter(Context context, List<Device> deviceList, boolean isClickable, OnItemClickListener listener) {
        this.deviceList = deviceList;
        this.isClickable = isClickable;
        this.onItemClickListener = listener;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_view, parent, false);
        return new MyViewHolder(v);
    }
    private Context context;

    public DeviceAdapter(Context context, List<Device> list) {
        this.context = context;
        this.deviceList = list;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Device device = deviceList.get(position);
        holder.name.setText(device.getName());
        holder.id.setText(device.getId());

        if (isClickable) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(device);
                    }
//                    int position=this.getAdapterposition();
                Device device = deviceList.get(position);
                String name=device.getName();
                String id=device.getId();
                Intent intent=new Intent(context, DeviceDisplayActivity.class);
                intent.putExtra("Rname",name);
                intent.putExtra("Rid",id);
                context.startActivity(intent);
                }

            });
        }
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
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

//
//public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
//    private Context context;
//    private List<Device> deviceList = new ArrayList<>();
//
//
//    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public TextView name;
//        public TextView id;
//        public myViewHolder(@NonNull View itemView){
//            super(itemView);
//            itemView.setOnClickListener(this);
//            name=itemView.findViewById(R.id.displayName);
//            id=itemView.findViewById(R.id.displayId);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
//    // Define the interface for item click events
//    public interface OnItemClickListener {
//        void onItemClick(Device device);
//    }
//    private OnItemClickListener onItemClickListener;
//
//    public DeviceAdapter(ArrayList<Device> deviceList, boolean b, OnItemClickListener listener) {
//        this.deviceList = deviceList;
//        this.onItemClickListener = listener;
//    }
//
//    public DeviceAdapter(Context context, List<Device> list) {
//        this.context = context;
//        this.deviceList = list;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.device_view, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        final Device device = deviceList.get(position);
//        holder.name.setText(device.getName());
//        holder.id.setText(device.getId());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(device);
//                }
////                int position=this.getAdapterposition();
//                Device device = deviceList.get(position);
//                String name=device.getName();
//                String id=device.getId();
//                Intent intent=new Intent(context, DeviceDisplayActivity.class);
//                intent.putExtra("Rname",name);
//                intent.putExtra("Rid",id);
//                context.startActivity(intent);
//            }
//
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        // Make sure deviceList is not null before accessing its size
//        return deviceList != null ? deviceList.size() : 0;
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        public View ButtonAdd;
//        TextView name, id;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.DName);
//            id = itemView.findViewById(R.id.DID);
//        }
//    }
//
//
//
//}
