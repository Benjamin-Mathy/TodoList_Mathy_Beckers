package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 18-04-17.
 */

public abstract class Notification {
    private long id;

    public long getId(){return this.id;}

    public void setId(long id) {
        this.id = id;
    }
}
