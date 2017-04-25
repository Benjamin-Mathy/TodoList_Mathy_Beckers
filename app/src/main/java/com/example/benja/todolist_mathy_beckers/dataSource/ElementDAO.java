package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.benja.todolist_mathy_beckers.database.ElementTable;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public class ElementDAO extends BaseDAO implements IElementDAO {
    protected ElementDAO(Context _context) {
        super(_context);
    }

    @Override
    public long createElement(Element element, int idTodo) {
        openW();

        ContentValues values = new ContentValues();

        //values.put(ElementTable.FeedEntry._ID, element.getId());
        values.put(ElementTable.FeedEntry.COLUMN_TEXT, element.getText());
        values.put(ElementTable.FeedEntry.COLUMN_INDEX, element.getIndex());
        values.put(ElementTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);

        long elementId = getDatabase().insert(ElementTable.FeedEntry.TABLE_NAME, null, values);

        close();

        return elementId;
    }
    @Override
    public long createElement(ElementImage element, int idTodo) {
        openW();

        ContentValues values = new ContentValues();

        //values.put(ElementTable.FeedEntry._ID, element.getId());
        values.put(ElementTable.FeedEntry.COLUMN_TEXT, element.getText());
        values.put(ElementTable.FeedEntry.COLUMN_INDEX, element.getIndex());
        values.put(ElementTable.FeedEntry.COLUMN_FK_TODOLIST, idTodo);
        values.put(ElementTable.FeedEntry.COLUMN_INDEX, idTodo);
        values.put(ElementTable.FeedEntry.COLUMN_IMAGE, element.getImage().toString());

        long elementId = getDatabase().insert(ElementTable.FeedEntry.TABLE_NAME, null, values);

        close();

        return elementId;
    }


    @Override
    public List<Element> readElement(int idTodolist) {
        Cursor cursor = getElements(idTodolist);

        List<Element> Elements = new ArrayList<>();
        while(cursor.moveToNext()) {
            Element currentElement = new Element();
            currentElement.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ElementTable.FeedEntry._ID)));
            currentElement.setIndex(cursor.getInt(cursor.getColumnIndexOrThrow(ElementTable.FeedEntry.COLUMN_INDEX)));
            currentElement.setText(cursor.getString(cursor.getColumnIndexOrThrow(ElementTable.FeedEntry.COLUMN_TEXT)));
            Elements.add(currentElement);
        }
        cursor.close();

        return Elements;
    }
    @Override
    public List<ElementImage> readElementImage(int idTodolist) {
        Cursor cursor = getElements(idTodolist);

        List<Element> Elements = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ElementTable.FeedEntry._ID));
            //itemIds.add(itemId);
        }
        cursor.close();

        close();
        return null;
    }
    @Override
    public List<Element> readElementSon(int idTodolist) {

        return null;
    }

    @Override
    public void updateElement(Element element) {

    }

    @Override
    public void deleteElement(Element element) {

    }
    public Cursor getElements(int idTodolist){
        openR();

        String[] projection = {
                ElementTable.FeedEntry._ID,
                ElementTable.FeedEntry.COLUMN_TEXT,
                ElementTable.FeedEntry.COLUMN_INDEX,
                ElementTable.FeedEntry.COLUMN_IMAGE,
                ElementTable.FeedEntry.COLUMN_SON
        };

        String selection = ElementTable.FeedEntry.COLUMN_FK_TODOLIST + " = ?";
        String[] selectionArgs = { Integer.toString(idTodolist) };

        String sortOrder =
                ElementTable.FeedEntry.COLUMN_INDEX + " DESC";

        Cursor cursor = getDatabase().query(
                ElementTable.FeedEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        close();
        return cursor;
    }
}
