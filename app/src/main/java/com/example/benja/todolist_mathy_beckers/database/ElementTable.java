package com.example.benja.todolist_mathy_beckers.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Max on 18-04-17.
 */

public class ElementTable {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "elements";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_SON = "son";
        public static final String COLUMN_INDEX = "index";
        public static final String COLUMN_FK_TODOLIST = "todolist";
    }
    private static final String DATABASE_CREATE = "create table "
            + FeedEntry.TABLE_NAME
            + "("
            + FeedEntry._ID + " integer primary key autoincrement, "
            + FeedEntry.COLUMN_TEXT + " text not null, "
            + FeedEntry.COLUMN_IMAGE + " text, "
            + FeedEntry.COLUMN_SON + " text, "
            + FeedEntry.COLUMN_INDEX + " integer not null, "
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
