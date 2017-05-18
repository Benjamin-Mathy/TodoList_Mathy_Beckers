package com.example.benja.todolist_mathy_beckers.presenter;

import com.example.benja.todolist_mathy_beckers.dataSource.IElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.INotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.ITodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;
import com.example.benja.todolist_mathy_beckers.view.ITodoImageActivity;

import java.util.List;

/**
 * Created by Max on 08-05-17.
 */

public class TodoImagePresenter extends BasePresenter implements ITodoImagePresenter {

    ITodoImageActivity view;

    private int todoId;

    public TodoImagePresenter(ITodoImageActivity view, ITodolistDAO daoTodo, IElementDAO daoElem, INotificationDAO daoNotif){
        super(daoTodo, daoElem, daoNotif);
        this.view = view;
    }

    @Override
    public void setTodoId(int id) {
        this.todoId = id;
    }

    @Override
    public List<ElementImage> getAllElements() {
        return getElementDAO().readElementImage(todoId);
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
}
