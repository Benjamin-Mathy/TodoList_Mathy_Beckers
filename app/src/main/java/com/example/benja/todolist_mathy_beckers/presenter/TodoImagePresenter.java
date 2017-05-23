package com.example.benja.todolist_mathy_beckers.presenter;

import android.app.Activity;
import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;
import com.example.benja.todolist_mathy_beckers.model.NotifManager;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.view.ITodoImageActivity;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max on 08-05-17.
 */

public class TodoImagePresenter extends BasePresenter implements ITodoImagePresenter {

    ITodoImageActivity view;
    NotifManager notifManager;

    private int todoId;

    public TodoImagePresenter(ITodoImageActivity view, ITodolistDAO daoTodo, IElementDAO daoElem){
        super(daoTodo, daoElem);
        this.view = view;
    }

    @Override
    public void setTodoId(int id) {
        this.todoId = id;
    }

    @Override
    public List<ElementImage> getAllElements() {
        List<ElementImage> elements = getElementDAO().readElementImage(todoId);
        Collections.sort(elements);
        return elements;
    }

    @Override
    public void addElement(String imagePath) {
        ElementImage element = new ElementImage();
        element.setImage(imagePath);
        element.setText("");
        getElementDAO().createElement(element, todoId);
    }

    @Override
    public void removeElement(ElementImage element) {
        getElementDAO().deleteElement(element);
    }

    @Override
    public void saveElements(List<ElementImage> elements) {
        for (ElementImage e : elements) {
            getElementDAO().updateElement(e, todoId);
        }
    }

    @Override
    public void setColor(String tag) {
        Todo todo = getTodoDAO().readTodolist(todoId);
        todo.setColor(Colors.valueOf(tag));
        getTodoDAO().updateTodolist(todo);
    }

    @Override
    public void setTitle(String title){
        Todo todo = getTodoDAO().readTodolist(todoId);
        todo.setName(title);
        getTodoDAO().updateTodolist(todo);
    }

    @Override
    public String getTitle(){
        return getTodoDAO().readTodolist(todoId).getName();
    }

    @Override
    public Colors getColor() {
        return getTodoDAO().readTodolist(todoId).getColor();
    }

    @Override
    public void addAlarm(Activity activity, long alarmTime) {
        notifManager = new NotifManager(activity);
        notifManager.addAlarm(alarmTime, getTodoDAO().readTodolist(todoId));
    }

    @Override
    public void removeAlarm(Activity activity) {
        notifManager = new NotifManager(activity);
        notifManager.removeAlarm(getTodoDAO().readTodolist(todoId));
    }
}
