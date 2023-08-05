package com.abidingtech.pick_by_click;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DeviceViewHolder extends RecyclerView.ViewHolder {
    TextView deviceNameView,deviceIdView;

    public DeviceViewHolder(@NonNull View itemView) {
        super(itemView);
        deviceNameView=itemView.findViewById(R.id.DName);
        deviceIdView=itemView.findViewById(R.id.DID);
    }
}
