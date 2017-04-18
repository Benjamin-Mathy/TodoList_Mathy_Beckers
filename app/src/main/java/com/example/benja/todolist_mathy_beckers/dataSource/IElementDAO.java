package com.example.benja.todolist_mathy_beckers.dataSource;

import com.example.benja.todolist_mathy_beckers.model.Element;

import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public interface IElementDAO {

    void createElement(Element element);
    List<Element> readElement(int idTodolist);
    void updateElement(Element element);
    void deleteElement(Element element);
}
