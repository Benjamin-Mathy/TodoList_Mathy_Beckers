package com.example.benja.todolist_mathy_beckers.presenter;

import android.graphics.Color;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.INotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 13-04-17.
 */

public class MainPresenter extends BasePresenter implements IMainPresenter{

    private IMainActivity view;

    public MainPresenter(IMainActivity view, ITodolistDAO daoTodo, IElementDAO daoElem, INotificationDAO daoNotif){
        super(daoTodo, daoElem, daoNotif);
        this.view = view;
    }

    @Override
    public List<Todo> getAllTodos() {
        return getTodoDAO().getTodolists();
    }

    @Override
    public void addTodo(TodoType type) {
        Todo newTodo = new Todo(0, type, "New Todolist", Colors.BLUE);
        getTodoDAO().createTodolist(newTodo);
    }

    @Override
    public int getLastTodoId() {
        List<Todo> todos = getAllTodos();
        int maxId = 0;
        for (Todo todo : todos) {
            if(maxId < todo.getId()){
                maxId = (int) todo.getId();
            }
        }
        return maxId;
    }

    @Override
    public void removeTodo(Todo todo) {
        getNotificationDAO().deleteNotification(getNotificationDAO().readNotificationGPS((int) todo.getId()));
        getNotificationDAO().deleteNotification(getNotificationDAO().readNotificationTime((int) todo.getId()));
        List<Element> elements = getElementDAO().readElement((int) todo.getId());
        for (Element e : elements) {
            getElementDAO().deleteElement(e);
        }
        getTodoDAO().deleteTodolist(todo);
    }

    @Override
    public TodoType getTypeOfTodo(int id) {
        return getTodoDAO().getTodolistType(id);
    }
}
