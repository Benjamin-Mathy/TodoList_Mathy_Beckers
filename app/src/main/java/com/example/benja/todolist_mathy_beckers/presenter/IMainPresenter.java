package com.example.benja.todolist_mathy_beckers.presenter;

import android.app.Activity;

import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import java.util.List;

/**
 * Created by Max on 13-04-17.
 */
public interface IMainPresenter {

    List<Todo> getAllTodos();
    void addTodo(TodoType type);
    int getLastTodoId();
    void removeTodo(Todo todo, Activity activity);
    TodoType getTypeOfTodo(int todoId);
    List<Todo> getTodosWith(String request);
}
