package com.example.benja.todolist_mathy_beckers.model;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Benja on 16-05-17.
 */

public class NotificationManager {

    Activity activity;
    public NotificationManager(Activity activity){
        this.activity = activity;
    }
    public void startAlert() {
        Intent intent = new Intent(activity, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                activity.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (5 * 1000), pendingIntent);
        Toast.makeText(activity, "Alarm set in " + 5 + " seconds",Toast.LENGTH_LONG).show();
    }
}
class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp=MediaPlayer.create(context,2  );
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
