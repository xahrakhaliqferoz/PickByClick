package com.abidingtech.pick_by_click;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.abidingtech.pick_by_click.classes.NotificationModel;
import com.abidingtech.pick_by_click.fragments.NotificationFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NotificationUtil {
    public static String [] getStringArrayFromList(List<String> data){
        return Arrays.copyOf(data.toArray(), data.size(), String[].class);
    }
    public static void showNotification(Context context, String title, String body){

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.abidingtech.app";
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification id",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(body);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        notificationBuilder
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setWhen(new Date().getTime())
                .setSmallIcon(R.drawable.logo)
                .setColor(ContextCompat.getColor(context,R.color.purple_700))
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body));
        Intent notificationIntent=new Intent(context, NotificationFragment.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);

        int id=565;
        Intent[] intentArray=new Intent[]{notificationIntent};
        PendingIntent pendingIntent= PendingIntent.getActivities(context,id,intentArray,0);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationCompat.BigTextStyle bigTextStyle=new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(body);
        notificationBuilder.setStyle(bigTextStyle);
        notificationManager.notify(id,notificationBuilder.build());

        String uid = FirebaseAuth.getInstance().getUid();
        if(uid != null){
            FirebaseDatabase.getInstance().getReference("Notifications")
                    .child(uid)
                    .push()
                    .setValue(new NotificationModel(title, body, new Date().getTime()));
        }


}
}