package com.example.deepank.art_therapy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class UserPresentBroadcastReceiver extends BroadcastReceiver {
    NotificationCompat.Builder notification;
    private static final int uniqueID=15661;
    ;
    public UserPresentBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.d("broadcast","broadcast received");       }
        notification=new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);

        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("Notification From Art Therapy");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Art Therapy");
        notification.setContentText("Draw your way to an artist!");


        Intent in = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);


        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
        notification.setAutoCancel(true);


    }
}
