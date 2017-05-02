package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.view.ITodoTextActivity;

/**
 * Created by Max on 14-04-17.
 */

public class TodoTextPresenter implements ITodoTextPresenter {

    ITodoTextActivity view;
    IElementDAO elementDAO;

    public TodoTextPresenter(ITodoTextActivity view, IElementDAO dao){
        this.view = view;
        this.elementDAO = dao;
    }
}
