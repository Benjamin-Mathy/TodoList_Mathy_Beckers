package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.benja.todolist_mathy_beckers.database.ElementTable;
import com.example.benja.todolist_mathy_beckers.database.TodolistTable;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public class TodolistDAO extends BaseDAO implements ITodolistDAO {
    protected TodolistDAO(Context _context) {
        super(_context);
    }

    @Override
    public long createTodolist(Todo todo) {
        openW();

        ContentValues values = new ContentValues();

        values.put(TodolistTable.FeedEntry.COLUMN_TYPE, todo.getType().toString());
        values.put(TodolistTable.FeedEntry.COLUMN_NAME, todo.getName());
        values.put(TodolistTable.FeedEntry.COLUMN_COLOR, todo.getColor().toString());

        long todoId = getDatabase().insert(TodolistTable.FeedEntry.TABLE_NAME, null, values);

        close();

        return todoId;
    }

    @Override
    public Todo readTodolist(int idTodolist) {
        return null;
    }

    @Override
    public void updateTodolist(Todo todo) {

    }

    @Override
    public void deleteTodolist(Todo todo) {

    }
}
