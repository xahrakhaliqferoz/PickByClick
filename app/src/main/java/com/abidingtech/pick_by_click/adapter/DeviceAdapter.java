package com.abidingtech.pick_by_click.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.activites.DeviceDisplayActivity;
import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.activites.SendNotificationActivity;
import com.abidingtech.pick_by_click.classes.Device;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> {
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

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete the device?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance()
                                .getReference("Devices")
                                .child(FirebaseAuth.getInstance().getUid())
                                .child(device.getId())
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            deviceList.remove(device);
                                            notifyDataSetChanged();
                                        } else {
                                            Toast.makeText(context, "Failed to delete device", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked No, do nothing
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
        if (isClickable) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(device);
                    }
                    String name = device.getName();
                    String id = device.getId();
                    Intent intent = new Intent(context, DeviceDisplayActivity.class);
                    intent.putExtra("Rname", name);
                    intent.putExtra("Rid", id);
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
        Button btnDel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.DName);
            id = itemView.findViewById(R.id.DID);
            btnDel = itemView.findViewById(R.id.btdeletebutton);
        }
    }
}


