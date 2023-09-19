package com.abidingtech.pick_by_click;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.classes.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

   Context context;
   ArrayList<NotificationModel> notificationModelArrayList;
    public NotificationAdapter(ArrayList<NotificationModel> notificationModelArrayList) {
    this.context=context;
    this.notificationModelArrayList=notificationModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.notification_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    NotificationModel notificationModel=notificationModelArrayList.get(position);
    holder.title.setText(NotificationModel.title);
    holder.body.setText(NotificationModel.body);
    holder.time.setText((int) NotificationModel.time);


    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,body,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tvTitle);
            body=itemView.findViewById(R.id.tvBody);
            time=itemView.findViewById(R.id.tvTime);

        }
    }
}
