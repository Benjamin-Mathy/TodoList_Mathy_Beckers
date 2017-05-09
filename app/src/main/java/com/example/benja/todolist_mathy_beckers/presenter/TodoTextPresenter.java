package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.view.ITodoTextActivity;

import java.util.List;

/**
 * Created by Max on 14-04-17.
 */

public class TodoTextPresenter implements ITodoTextPresenter {

    ITodoTextActivity view;
    IElementDAO elementDAO;
    ITodolistDAO todoDAO;

    int todoId;

    public TodoTextPresenter(ITodoTextActivity view, IElementDAO elementDAO, ITodolistDAO todoDAO){
        this.view = view;
        this.elementDAO = elementDAO;
        this.todoDAO = todoDAO;
    }

    public void setTodoId(int id){
        this.todoId = id;
    }

    @Override
    public List<Element> getAllElements() {
        return todoDAO.readTodolist(todoId).getElements();
    }
}
