package com.example.benja.todolist_mathy_beckers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Benja on 18-04-17.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "2Dew.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        TodolistTable.onCreate(db);
        ElementTable.onCreate(db);
        NotificationTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TodolistTable.onUpgrade(db, oldVersion, newVersion);
        ElementTable.onUpgrade(db, oldVersion, newVersion);
        NotificationTable.onUpgrade(db, oldVersion, newVersion);
    }
}