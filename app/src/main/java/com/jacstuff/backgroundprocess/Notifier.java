package com.jacstuff.backgroundprocess;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notifier {

    private final Context context;
    private NotificationManager notificationManager;
    private Notification.Builder notificationBuilder;


    public Notifier(Context context, String message, String title){
        log("Entered Notifier constructor, message: " + message);
        this.context = context;
        this.createNotification(message, title);
        issueNotification();
    }


    private void log(String msg){
        System.out.println("Notifier:" +  msg);
        System.out.flush();
    }


    private void createNotification(String message, String title){
        log("Entered createNotification()");
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationBuilder = new Notification.Builder(context);
        notificationBuilder.setSmallIcon(R.drawable.ic_action_star);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);

        log("createNotification() - set the title and text on the notification builder");
        Intent notificationIntent = new Intent(context, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);
        log(" createNotification() - set the contentIntent.");
    }

    private int notificationId = 0;

    private void issueNotification(){
        Notification notification2 = new Notification.Builder(context.getApplicationContext())
                .setSmallIcon(R.drawable.ic_action_star)
                .setContentTitle("title 2")
                .setContentText("ContentText2")
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();

        notificationManager.notify(2, notification2);

        log("Entered issueNotification()");
        notificationBuilder.setWhen(System.currentTimeMillis() + 3000);
        log("issueNotification() - set the current time for notification issue");
        notificationId++;
        log("issueNotification() - about to call the notifyManager to issue the notification");
        notificationManager.notify(notificationId, notificationBuilder.build());
        log("issueNotification() - called the notifyManager to issue the notification");

    }

}