package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.benja.todolist_mathy_beckers.database.DBHelper;

/**
 * Created by Max on 18-04-17.
 */

public abstract class BaseDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    protected BaseDAO(Context _context){
        dbHelper = new DBHelper(_context);
    }

    protected void openW() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    protected void openR() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    protected void close() {
        dbHelper.close();
    }

    protected SQLiteDatabase getDatabase(){
        return database;
    }
}

