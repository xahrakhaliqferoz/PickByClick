package com.abidingtech.pick_by_click.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abidingtech.pick_by_click.R;
import com.abidingtech.pick_by_click.classes.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    //   Context context;
    ArrayList<NotificationModel> notificationModelArrayList;

    public NotificationAdapter(ArrayList<NotificationModel> notificationModelArrayList) {
        this.notificationModelArrayList = notificationModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view, parent, false);
        return new MyViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NotificationModel notificationModel = notificationModelArrayList.get(position);

//        NotificationModel notificationModel = notificationModelArrayList.get(position);
        holder.title.setText(notificationModel.getTitle());
        holder.body.setText(notificationModel.getBody());
        holder.time.setText(String.valueOf(notificationModel.getTime()));


    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, body, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            body = itemView.findViewById(R.id.tvBody);
            time = itemView.findViewById(R.id.tvTime);

        }
    }
}
