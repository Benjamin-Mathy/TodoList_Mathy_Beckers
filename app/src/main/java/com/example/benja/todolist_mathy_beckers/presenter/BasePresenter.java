package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;

/**
 * Created by Max on 16-05-17.
 */

public class BasePresenter {

    private ITodolistDAO todoDAO;
    private IElementDAO elementDAO;

    public BasePresenter(ITodolistDAO todoDAO, IElementDAO elementDAO){
        this.todoDAO = todoDAO;
        this.elementDAO = elementDAO;
    }

    public ITodolistDAO getTodoDAO(){
        return todoDAO;
    }

    public IElementDAO getElementDAO(){
        return elementDAO;
    }
}
