package com.example.benja.todolist_mathy_beckers.dataSource;

import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import java.util.List;

/**
 * Created by Max on 18-04-17.
 */

public interface ITodolistDAO {

    long createTodolist(Todo todo);
    Todo readTodolist(int idTodolist);
    void updateTodolist(Todo todo);
    void deleteTodolist(Todo todo);
    List<Todo>getTodolists();
    TodoType getTodolistType(int idTodolist);
}
