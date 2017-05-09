package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.model.Element;

import java.util.List;

/**
 * Created by Max on 14-04-17.
 */

public interface ITodoTextPresenter {

    List<Element> getAllElements();

    void setTodoId(int id);
}
