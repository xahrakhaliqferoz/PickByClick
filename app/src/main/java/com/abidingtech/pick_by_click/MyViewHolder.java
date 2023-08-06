package com.abidingtech.pick_by_click;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class MyViewHolder extends RecyclerView.ViewHolder{
    TextView name,id;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.DName);
        id=itemView.findViewById(R.id.DID);


    }
}

