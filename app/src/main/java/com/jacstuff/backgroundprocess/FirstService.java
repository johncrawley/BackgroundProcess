package com.jacstuff.backgroundprocess;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class FirstService extends Service {

    private final Context context;
    public static final String QUERIES = "queries";
    private final IBinder mBinder = new MyBinder();


    public FirstService() {
        context = FirstService.this;
    }

    /*
        Service.START_STICKY - service is restarted if terminated, intent passed in has null value
        Service.START_NOT_STICKY - service is not restarted
        Service.START_REDELIVER_INTENT - service is restarted if terminated, original intent is passed in

     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        //WebsiteQuery[] queries =(WebsiteQuery[]) intent.getParcelableArrayExtra("queries");
        //notifier.createNotification("message for you: you have been notified! ", "title");
        //for(WebsiteQuery query : queries){
         //   Log.i("FirstService", " msg: " + query.getSuccessMessage());
        //}

        Log.i("FirstService", " hello! onStartCommand() initiated!");
        new Notifier(context, "Hello there", "Title!!");
        return Service.START_REDELIVER_INTENT;
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("FirstService", "Entered onBind()");
        return mBinder;
    }


    public class MyBinder extends Binder {
        FirstService getService(){
            return FirstService.this;
        }
    }

}
