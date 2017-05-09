package com.example.benja.todolist_mathy_beckers.presenter;

import android.graphics.Color;

import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 13-04-17.
 */

public class MainPresenter implements IMainPresenter {

    private IMainActivity view;
    private ITodolistDAO todoDAO;

    public MainPresenter(IMainActivity view, ITodolistDAO dao){
        this.view = view;
        this.todoDAO = dao;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDAO.getTodolists();
    }

    @Override
    public void addTodo(TodoType type) {
        Todo newTodo = new Todo(0, type, "New Todolist", Colors.BLUE);
        todoDAO.createTodolist(newTodo);
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
        todoDAO.deleteTodolist(todo);
    }
}
