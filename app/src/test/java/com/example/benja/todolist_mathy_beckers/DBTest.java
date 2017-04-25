package com.example.benja.todolist_mathy_beckers;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.database.DBHelper;
import com.example.benja.todolist_mathy_beckers.model.Element;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DBTest extends AndroidTestCase {

    @Test
    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DBHelper.DATABASE_NAME));
    }

    @Test
    public void testCreateDB(){
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }
}