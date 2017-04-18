package com.example.benja.todolist_mathy_beckers.dataSource;

import com.example.benja.todolist_mathy_beckers.model.Notification;

/**
 * Created by Max on 18-04-17.
 */

public interface INotificationDAO {

    void createNotification(Notification notification);
    Notification readNotification(int idTodolist);
    void updateNotification(Notification notification);
    void deleteNotification(Notification notification);
}
