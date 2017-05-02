package com.example.benja.todolist_mathy_beckers.dataSource;

import com.example.benja.todolist_mathy_beckers.model.Notification;
import com.example.benja.todolist_mathy_beckers.model.NotificationGPS;
import com.example.benja.todolist_mathy_beckers.model.NotificationTime;

import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public interface INotificationDAO {

    long createNotification(NotificationTime notification, int idTodo);
    long createNotification(NotificationGPS notification, int idTodo);
    NotificationTime readNotificationTime(int idTodolist);
    NotificationGPS readNotificationGPS(int idTodolist);
    void updateNotification(NotificationTime notification, int idTodo);
    void updateNotification(NotificationGPS notification, int idTodo);
    void deleteNotification(Notification notification);
}
