package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.benja.todolist_mathy_beckers.database.NotificationTable;
import com.example.benja.todolist_mathy_beckers.model.Notification;
import com.example.benja.todolist_mathy_beckers.model.NotificationGPS;
import com.example.benja.todolist_mathy_beckers.model.NotificationTime;

import java.sql.Date;

/**
 * Created by Max on 18-04-17.
 */

public class NotificationDAO extends BaseDAO implements INotificationDAO {
    public NotificationDAO(Context _context) {
        super(_context);
    }
    @Override
    public long createNotification(NotificationTime notification, int idTodo) {
        openW();

        ContentValues values = new ContentValues();

        values.put(NotificationTable.FeedEntry.COLUMN_DATE, notification.getDate().getTime());
        values.put(NotificationTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);

        long notificationId = getDatabase().insert(NotificationTable.FeedEntry.TABLE_NAME, null, values);

        close();

        return notificationId;
    }

    @Override
    public long createNotification(NotificationGPS notification, int idTodo) {
        openW();

        ContentValues values = new ContentValues();

        values.put(NotificationTable.FeedEntry.COLUMN_LATITUDE, notification.getLatitude());
        values.put(NotificationTable.FeedEntry.COLUMN_LONGITUDE, notification.getLongitude());
        values.put(NotificationTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);

        long notificationId = getDatabase().insert(NotificationTable.FeedEntry.TABLE_NAME, null, values);

        close();

        return notificationId;
    }

    @Override
    public NotificationTime readNotificationTime(int idTodolist) {
        openR();

        Cursor cursor = getNotifications(idTodolist);

        if( cursor != null && cursor.moveToFirst() ){
            NotificationTime currentNotification = new NotificationTime();
            currentNotification.setId(cursor.getLong(cursor.getColumnIndexOrThrow(NotificationTable.FeedEntry._ID)));
            currentNotification.setDate(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(NotificationTable.FeedEntry.COLUMN_DATE))));

            cursor.close();
            close();
            return currentNotification;
        }
        return null;
    }

    @Override
    public NotificationGPS readNotificationGPS(int idTodolist) {
        openR();
        Cursor cursor = getNotifications(idTodolist);

        if( cursor != null && cursor.moveToFirst() ){
            NotificationGPS currentNotification = new NotificationGPS();

            currentNotification.setId(cursor.getLong(cursor.getColumnIndexOrThrow(NotificationTable.FeedEntry._ID)));
            currentNotification.setLatitude(cursor.getLong(cursor.getColumnIndexOrThrow(NotificationTable.FeedEntry.COLUMN_LATITUDE)));
            currentNotification.setLongitude(cursor.getLong(cursor.getColumnIndexOrThrow(NotificationTable.FeedEntry.COLUMN_LONGITUDE)));

            cursor.close();
            close();
            return currentNotification;
        }
        return null;
    }

    @Override
    public void updateNotification(NotificationTime notification, int idTodo) {
        openW();

        ContentValues values = new ContentValues();
        values.put(NotificationTable.FeedEntry.COLUMN_DATE, notification.getDate().getTime());
        values.put(NotificationTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);;

        String selection = NotificationTable.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(notification.getId())};

        int count = getDatabase().update(
                NotificationTable.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        close();
    }

    @Override
    public void updateNotification(NotificationGPS notification, int idTodo) {
        openW();

        ContentValues values = new ContentValues();
        values.put(NotificationTable.FeedEntry.COLUMN_LATITUDE, notification.getLatitude());
        values.put(NotificationTable.FeedEntry.COLUMN_LONGITUDE, notification.getLongitude());
        values.put(NotificationTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);

        String selection = NotificationTable.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(notification.getId())};

        int count = getDatabase().update(
                NotificationTable.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        close();
    }

    @Override
    public void deleteNotification(Notification notification) {
        if(notification == null){
            return;
        }
        String selection = NotificationTable.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(notification.getId())};

        getDatabase().delete(NotificationTable.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }
    public Cursor getNotifications(int idTodolist){
        String[] projection = {
                NotificationTable.FeedEntry._ID,
                NotificationTable.FeedEntry.COLUMN_DATE,
                NotificationTable.FeedEntry.COLUMN_LATITUDE,
                NotificationTable.FeedEntry.COLUMN_LONGITUDE
        };

        String selection = NotificationTable.FeedEntry.COLUMN_FK_TODOLIST + " = ?";
        String[] selectionArgs = { Integer.toString(idTodolist) };

        Cursor cursor = getDatabase().query(
                NotificationTable.FeedEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        return cursor;
    }
}
