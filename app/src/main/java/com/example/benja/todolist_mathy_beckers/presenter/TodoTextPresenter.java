package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.INotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.view.ITodoTextActivity;

import java.util.List;

/**
 * Created by Max on 14-04-17.
 */

public class TodoTextPresenter extends BasePresenter implements ITodoTextPresenter {

    ITodoTextActivity view;

    int todoId;

    public TodoTextPresenter(ITodoTextActivity view, ITodolistDAO daoTodo, IElementDAO daoElem, INotificationDAO daoNotif){
        super(daoTodo, daoElem, daoNotif);
        this.view = view;
    }

    public void setTodoId(int id){
        this.todoId = id;
    }

    @Override
    public List<Element> getAllElements() {
        return getElementDAO().readElement(todoId);
    }

    @Override
    public void addElement() {
        Element element = new Element("New Element", getElementDAO().readElement(todoId).size());
        getElementDAO().createElement(element, todoId);
    }
}
