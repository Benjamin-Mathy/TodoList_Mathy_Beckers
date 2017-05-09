package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.view.ITodoImageActivity;
import com.example.benja.todolist_mathy_beckers.view.ITodoTextActivity;

/**
 * Created by Max on 08-05-17.
 */

public class TodoImagePresenter implements ITodoImagePresenter {

    ITodoImageActivity view;
    IElementDAO elementDAO;

    public TodoImagePresenter(ITodoImageActivity view, IElementDAO dao){
        this.view = view;
        this.elementDAO = dao;
    }
}
