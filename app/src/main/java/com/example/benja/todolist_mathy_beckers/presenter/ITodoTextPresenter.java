package com.example.benja.todolist_mathy_beckers.presenter;

import android.app.Activity;
import android.graphics.Color;

import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Element;

import java.util.List;

/**
 * Created by Max on 14-04-17.
 */

public interface ITodoTextPresenter {

    List<Element> getAllElements();

    void setTodoId(int id);

    void addElement();

    void removeElement(Element element);

    void saveElements(List<Element> elements);

    void setTitle(String title);

    String getTitle();

    void setColor(String tag);

    Colors getColor();

    void addAlarm(Activity activity, long alarmTime, int todoid);
}
