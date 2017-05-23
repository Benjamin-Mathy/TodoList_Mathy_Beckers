package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.view.IMainActivity;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Max on 13-04-17.
 */

public class MainPresenter extends BasePresenter implements IMainPresenter{

    private IMainActivity view;

    public MainPresenter(IMainActivity view, ITodolistDAO daoTodo, IElementDAO daoElem){
        super(daoTodo, daoElem);
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

    @Override
    public List<Todo> getTodosWith(String request) {
        List<Todo> todos = getAllTodos();
        for (Iterator<Todo> iter = todos.listIterator(); iter.hasNext(); ) {
            Todo t = iter.next();
            if (!t.getName().toLowerCase().contains(request.toLowerCase())) {
                iter.remove();
            }
        }
        return todos;
    }
}
