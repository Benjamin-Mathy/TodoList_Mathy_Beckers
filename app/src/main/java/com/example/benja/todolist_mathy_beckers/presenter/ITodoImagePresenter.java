package com.example.benja.todolist_mathy_beckers.presenter;

import android.net.Uri;

import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;

import java.util.List;

/**
 * Created by Max on 08-05-17.
 */

public interface ITodoImagePresenter {

    List<ElementImage> getAllElements();

    void setTodoId(int id);

    void addElement(String imagePath);

    void removeElement(ElementImage element);

    void saveElements(List<ElementImage> elements);
}
