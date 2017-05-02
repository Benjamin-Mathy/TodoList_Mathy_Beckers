package com.example.benja.todolist_mathy_beckers.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Max on 18-04-17.
 */

public class TodolistTable {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "todolists";
        public static final String COLUMN_TYPE = "type";
        public static final String _ID = "rowid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COLOR = "color";
    }

    private static final String DATABASE_CREATE = "create table "
            + FeedEntry.TABLE_NAME
            + "("
            + FeedEntry._ID + "integer primary key,"
            + FeedEntry.COLUMN_TYPE + " text not null,"
            + FeedEntry.COLUMN_NAME + " text not null, "
            + FeedEntry.COLUMN_COLOR + " text "
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


    public static String GetTableName(){
        return FeedEntry.TABLE_NAME;
    }
    public static String GetIdName(){
        return FeedEntry._ID;
    }
}
