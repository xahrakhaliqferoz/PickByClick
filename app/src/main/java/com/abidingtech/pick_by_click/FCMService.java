package com.abidingtech.pick_by_click;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e( "FCMService: ", token+"");

    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        if (notification != null) {
            Log.e("FCMService: ", notification.getTitle() + "  " + notification.getBody());
            //call show notification
        } else
            Log.e("FCMService: ", remoteMessage.getData()+"");
}
}



