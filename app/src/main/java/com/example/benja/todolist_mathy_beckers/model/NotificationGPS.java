package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 18-04-17.
 */

public class NotificationGPS extends Notification {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
