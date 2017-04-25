package com.example.benja.todolist_mathy_beckers.model;

import java.util.Date;

/**
 * Created by Max on 18-04-17.
 */

public class NotificationTime extends Notification {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
