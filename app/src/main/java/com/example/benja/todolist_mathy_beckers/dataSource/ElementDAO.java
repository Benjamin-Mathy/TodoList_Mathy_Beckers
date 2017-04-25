package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;

import com.example.benja.todolist_mathy_beckers.database.ElementTable;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;

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

        //values.put(ElementTable.FeedEntry.COLUMN_ID, element.getId());
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

        //values.put(ElementTable.FeedEntry.COLUMN_ID, element.getId());
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
        return null;
    }

    @Override
    public void updateElement(Element element) {

    }

    @Override
    public void deleteElement(Element element) {

    }
}
