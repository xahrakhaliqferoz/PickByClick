package com.abidingtech.pick_by_click;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class MyViewHolder extends RecyclerView.ViewHolder{
    TextView Name,ID;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.DName);
        ID=itemView.findViewById(R.id.DID);


    }
}

