package com.example.benja.todolist_mathy_beckers.model;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.NotificationCompat;

import com.example.benja.todolist_mathy_beckers.R;

/**
 * Created by Youba on 22/05/2017.
 */

public class AlertLauncher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mNotifyBuilder;
        // Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle(intent.getStringExtra("name"))
                .setContentText("You've received new messages.")
                //.setColor(Color.parseColor(intent.getStringExtra("color")))
                .setSmallIcon(R.drawable.ic_alarm_24dp)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        int numMessages = 0;
        // Start of a loop that processes data and then notifies the user

        mNotifyBuilder.setContentText("2Dew")
                .setNumber(++numMessages);
        // Because the ID remains unchanged, the existing notification is
        // updated.
        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());

    }
}

