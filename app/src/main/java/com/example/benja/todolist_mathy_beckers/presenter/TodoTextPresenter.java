package com.example.benja.todolist_mathy_beckers.presenter;

import android.app.Activity;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.INotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.NotifManager;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.view.ITodoTextActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max on 14-04-17.
 */

public class TodoTextPresenter extends BasePresenter implements ITodoTextPresenter {

    ITodoTextActivity view;
    NotifManager notifManager;

    private int todoId;

    public TodoTextPresenter(ITodoTextActivity view, ITodolistDAO daoTodo, IElementDAO daoElem, INotificationDAO daoNotif){
        super(daoTodo, daoElem, daoNotif);
        this.view = view;
    }

    public void setTodoId(int id){
        this.todoId = id;
    }

    @Override
    public List<Element> getAllElements() {
        List<Element> elements = getElementDAO().readElement(todoId);
        Collections.sort(elements);
        return elements;
    }

    @Override
    public void addElement() {
        Element element = new Element("", getElementDAO().readElement(todoId).size());
        getElementDAO().createElement(element, todoId);
    }

    @Override
    public void removeElement(Element element) {
        getElementDAO().deleteElement(element);
    }

    @Override
    public void saveElements(List<Element> elements) {
        for (Element e : elements) {
            getElementDAO().updateElement(e, todoId);
        }
    }

    @Override
    public void setTitle(String title){
        Todo todo = getTodoDAO().readTodolistOnly(todoId);
        todo.setName(title);
        getTodoDAO().updateTodolist(todo);
    }

    @Override
    public String getTitle(){
        return getTodoDAO().readTodolistOnly(todoId).getName();
    }

    @Override
    public void setColor(String tag) {
        Todo todo = getTodoDAO().readTodolistOnly(todoId);
        todo.setColor(Colors.valueOf(tag));
        getTodoDAO().updateTodolist(todo);
    }

    @Override
    public Colors getColor() {
        return getTodoDAO().readTodolistOnly(todoId).getColor();
    }

    @Override
    public void addAlarm(Activity activity, long alarmTime, int todoId) {
        notifManager = new NotifManager(activity);
        notifManager.AddAlarm(alarmTime, todoId);
    }
}
