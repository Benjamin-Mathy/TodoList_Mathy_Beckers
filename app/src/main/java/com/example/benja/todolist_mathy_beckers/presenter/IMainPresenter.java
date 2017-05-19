package com.example.benja.todolist_mathy_beckers.presenter;

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

    void removeTodo(Todo todo);

    TodoType getTypeOfTodo(int todoId);
}
