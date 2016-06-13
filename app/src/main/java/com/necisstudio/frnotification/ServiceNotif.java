package com.necisstudio.frnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Jarcode on 2016-05-29.
 */

public class ServiceNotif extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("notif", "From: " + remoteMessage.getFrom());
        Log.e("notif", "Notification Message Body: " + remoteMessage.getNotification().getBody());
        //sendNotification(remoteMessage.getNotification().getBody());
        sendNotificationAndroidN(remoteMessage.getNotification().getBody());
    }

    private void sendNotificationAndroidN(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            String KEY_TEXT_REPLY = "key_text_reply";
            android.app.RemoteInput remoteInput = new android.app.RemoteInput.Builder(KEY_TEXT_REPLY)
                    .setLabel("Replay Text")
                    .build();
            Notification.Action action =
                    new Notification.Action.Builder(R.mipmap.ic_launcher,
                            "Replay Message", pendingIntent)
                            .addRemoteInput(remoteInput)
                            .build();
            Notification newMessageNotification =
                    new Notification.Builder(this)
//                            .setStyle(new Notification.MessagingStyle("Me")
//                                    .setConversationTitle("Team lunch")
//                                    .addMessage("Hi", 10, null) // Pass in null for user.
//                                    .addMessage("What's up?", 10, "Coworker")
//                                    .addMessage("Not much", 10, null)
//                                    .addMessage("How about lunch?", 10, "Coworker"))
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Android N Notification")
                            .setContentText(message)

                            .addAction(action)
                            .build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, newMessageNotification);
        }

    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Necis Notification")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
