package com.example.benja.todolist_mathy_beckers.model;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Benja on 16-05-17.
 */

public class NotifManager {
    public AlarmManager alarmManager;
    Activity activity;

    public NotifManager(Activity activity) {
        this.activity = activity;
        this.alarmManager = (AlarmManager) activity.getApplicationContext().getSystemService(ALARM_SERVICE);
    }

    public void addAlarm(long alarmTime, Todo todolist) {

        Intent intent = new Intent(activity, AlertLauncher.class );
        intent.putExtra("name", todolist.getName());
        intent.putExtra("color", todolist.getColor());
        PendingIntent pi = PendingIntent.getBroadcast(activity.getApplicationContext(), (int)todolist.getId(), intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pi);
    }
    public void removeAlarm(Todo todolist) {
        Intent intent = new Intent(activity, AlertLauncher.class );
        intent.putExtra("name", todolist.getName());
        intent.putExtra("color", todolist.getColor());
        PendingIntent pi = PendingIntent.getBroadcast(activity.getApplicationContext(), (int)todolist.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pi);
    }
}


