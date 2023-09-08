package com.abidingtech.pick_by_click;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class FCMService extends FirebaseMessagingService {
    Map<String, String> map = new HashMap<>();

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e("FCMService: ", token + "");

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();


        if (notification != null) {
            Log.e("FCMService: ", notification.getTitle() + "  " + notification.getBody());
            NotificationUtil.showNotification(FCMService.this, notification.getTitle(), notification.getBody());

            //call show notification

        } else{
            Log.e("FCMService: ", remoteMessage.getData() + "");

            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            NotificationUtil.showNotification(FCMService.this, title, body);

        }

    }
}



