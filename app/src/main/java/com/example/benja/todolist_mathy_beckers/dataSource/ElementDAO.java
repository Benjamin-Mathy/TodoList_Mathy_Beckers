package com.example.benja.todolist_mathy_beckers.dataSource;

import android.content.ContentValues;
import android.content.Context;

import com.example.benja.todolist_mathy_beckers.model.Element;

import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public class ElementDAO extends BaseDAO implements IElementDAO {
    protected ElementDAO(Context _context) {
        super(_context);
    }

    @Override
    public void createElement(Element element) {
        openW();

        ContentValues values = new ContentValues();
        values.put()
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
