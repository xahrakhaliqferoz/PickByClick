package com.abidingtech.pick_by_click;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterNotifi extends RecyclerView.Adapter<MyAdapterNotifi.MyViewHolder> {
    Context context;
    ArrayList<UserNotifi> list;

    public MyAdapterNotifi(Context context, ArrayList<UserNotifi>list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v=LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserNotifi user = list.get(position);
        holder.username.setText(user.getName());
        holder.id.setText(user.getID());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           username=itemView.findViewById(R.id.textname);
            id =itemView.findViewById(R.id.textid);
        }
    }
}
