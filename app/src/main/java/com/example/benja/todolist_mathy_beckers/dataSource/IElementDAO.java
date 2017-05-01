package com.example.benja.todolist_mathy_beckers.dataSource;

import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;

import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public interface IElementDAO {

    long createElement(Element element, int idTodo);
    long createElement(ElementImage element, int idTodo);
    List<Element> readElement(int idTodo);
    List<ElementImage> readElementImage(int idTodo);
    List<Element> readElementSon(int idTodo);
    void updateElement(Element element, int idTodo);
    void updateElement(ElementImage element, int idTodo);
    void deleteElement(Element element);
}
