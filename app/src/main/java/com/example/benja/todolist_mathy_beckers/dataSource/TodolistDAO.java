package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.benja.todolist_mathy_beckers.database.TodolistTable;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public class TodolistDAO extends BaseDAO implements ITodolistDAO {

    Context context;

    public TodolistDAO(Context _context) {
        super(_context);
        context=_context;
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
    public TodoType getTodolistType(int idTodolist){
        openR();

        Cursor cursor = getTodo(idTodolist);
        TodoType type;
        cursor.moveToFirst();
        type = TodoType.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_TYPE)));

        cursor.close();
        close();
        return type;
    }

    @Override
    public Todo readTodolist(int idTodolist) {
        openR();
        ElementDAO elementDAO = new ElementDAO(this.context);

        Cursor cursor = getTodo(idTodolist);


        Todo todo = new Todo();

        cursor.moveToFirst();

        todo.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry._ID)));
        todo.setName(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_NAME)));
        todo.setColor(Colors.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_COLOR))));
        todo.setType(TodoType.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_TYPE))));

        for (Element elem: elementDAO.readElement(idTodolist)) {
            todo.add(elem);
        }
        for (Element elem: elementDAO.readElementImage(idTodolist)) {
            todo.add(elem);
        }
        for (Element elem: elementDAO.readElementSon(idTodolist)) {
            todo.add(elem);
        }

        cursor.close();
        close();
        return todo;
    }

    @Override
    public Todo readTodolistOnly(int idTodolist){
        openR();

        Cursor cursor = getTodo(idTodolist);

        Todo todo = new Todo();

        cursor.moveToFirst();

        todo.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry._ID)));
        todo.setName(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_NAME)));
        todo.setColor(Colors.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_COLOR))));
        todo.setType(TodoType.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_TYPE))));

        cursor.close();
        close();
        return todo;
    }

    @Override
    public void updateTodolist(Todo todo) {
        openW();

        ContentValues values = new ContentValues();
        values.put(TodolistTable.FeedEntry.COLUMN_NAME, todo.getName());
        values.put(TodolistTable.FeedEntry.COLUMN_COLOR, todo.getColor().toString());
        values.put(TodolistTable.FeedEntry.COLUMN_TYPE, todo.getType().toString());

        String selection = TodolistTable.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(todo.getId())};

        int count = getDatabase().update(
                TodolistTable.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        close();
    }

    @Override
    public void deleteTodolist(Todo todo) {
        openW();

        String selection = TodolistTable.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(todo.getId())};

        getDatabase().delete(TodolistTable.FeedEntry.TABLE_NAME, selection, selectionArgs);

        close();
    }

    @Override
    public List<Todo> getTodolists() {
        openR();

        String[] projection = {
                TodolistTable.FeedEntry._ID,
                TodolistTable.FeedEntry.COLUMN_NAME,
                TodolistTable.FeedEntry.COLUMN_COLOR,
                TodolistTable.FeedEntry.COLUMN_TYPE
        };

        Cursor cursor = getDatabase().query(
                TodolistTable.FeedEntry.TABLE_NAME,         // The table to query
                projection,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );

        List<Todo> todolists = new ArrayList<>();

        while(cursor.moveToNext()) {
            Todo todo = new Todo();
            todo.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry._ID)));
            todo.setName(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_NAME)));
            todo.setColor(Colors.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_COLOR))));
            todo.setType(TodoType.valueFor(cursor.getString(cursor.getColumnIndexOrThrow(TodolistTable.FeedEntry.COLUMN_TYPE))));
            todolists.add(todo);
        }

        cursor.close();
        close();
        return todolists;
    }

    public Cursor getTodo(int idTodolist){
        String[] projection = {
                TodolistTable.FeedEntry._ID,
                TodolistTable.FeedEntry.COLUMN_NAME,
                TodolistTable.FeedEntry.COLUMN_COLOR,
                TodolistTable.FeedEntry.COLUMN_TYPE
        };

        String selection = TodolistTable.FeedEntry._ID + " = ?";
        String[] selectionArgs = { Integer.toString(idTodolist) };

        Cursor cursor = getDatabase().query(
                TodolistTable.FeedEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );


        return cursor;
    }
}
