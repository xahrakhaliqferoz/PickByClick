package com.abidingtech.pick_by_click;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView,phoneView,timeView;

    public MyViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageview);
        nameView=itemView.findViewById(R.id.name);
        phoneView=itemView.findViewById(R.id.phone);
        timeView=itemView.findViewById(R.id.time);
    }
}
