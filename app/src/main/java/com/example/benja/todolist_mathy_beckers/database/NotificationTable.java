package com.example.benja.todolist_mathy_beckers.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Max on 18-04-17.
 */

public class NotificationTable {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "notifications";
        public static final String _ID = "rowid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_FK_TODOLIST = "todolist";
    }

    private static final String DATABASE_CREATE = "create table "
            + FeedEntry.TABLE_NAME
            + "("
            + FeedEntry._ID + " integer primary key, "
            + FeedEntry.COLUMN_DATE + " datetime default current_timestramp, "
            + FeedEntry.COLUMN_LONGITUDE + " real, "
            + FeedEntry.COLUMN_LATITUDE + " real, "
            + FeedEntry.COLUMN_FK_TODOLIST + " integer, "
            + "foreign key("+FeedEntry.COLUMN_FK_TODOLIST+") references "+TodolistTable.GetTableName()+"("+TodolistTable.GetIdName()+")"
            + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(SQL_DELETE_ENTRIES);
        onCreate(database);
    }
}
