package com.jacstuff.backgroundprocess;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log("onCreate() about to create new intent");
       // intent = new Intent(this, WebsiteQuery.class);
        intent = new Intent(Intent.ACTION_SYNC,null, this, FirstService.class);
        // add infos for the service which file to download and where to store
        WebsiteQuery[] queries = new WebsiteQuery[]{new WebsiteQuery("url", "query", "success message", true)};
        intent.putExtra(FirstService.QUERIES, queries);
        log("onCreate() about to start service");
        startService(intent);

        //stopService(intent);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        //textView.setText("Service started");
    }


    @Override
    public void onClick(View view){
        stopService(intent);
        Notification notification3 = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_action_star)
                .setContentTitle("title 3")
                .setContentText("ContentText3")
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(5, notification3);
        Log.i("Main", "hello, clicked the button!");

        Notification notification4 = new NotificationCompat.Builder(getBaseContext(),"notification_id")
                .setSmallIcon(R.drawable.ic_action_star)
                .setContentTitle("title")
                .setContentText("content")
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();
        notificationManager.notify(0, notification4);
        createNotification();
    }


    public void createNotification(){

        log("Entered createNotification()");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder notificationBuilder  = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_action_star);
        notificationBuilder.setContentTitle("Immersion!!!");
        notificationBuilder.setContentText("Immersion Reminder!");

        log("createNotification() - set the title and text on the notification builder");
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        log("Entered issueNotification()");
        notificationBuilder.setWhen(System.currentTimeMillis());
        log("issueNotification() - set the current time for notification issue");
        int mNotificationId = 1;
        log("issueNotification() - about to call the notifyManager to issue the notification");
        notificationManager.notify(mNotificationId, notificationBuilder.build());

        log("issueNotification() - called the notifyManager to issue the notification");
        log(" createNotification() - set the contentIntent.");
    }





    private void log(String msg){
        System.out.println("MainActivity: " + msg);
    }





}
