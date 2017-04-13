package com.example.benja.todolist_mathy_beckers.presenter;

import android.graphics.Color;

import com.example.benja.todolist_mathy_beckers.model.Colors;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 13-04-17.
 */

public class MainPresenter implements IMainPresenter {

    private IMainActivity view;

    public MainPresenter(IMainActivity view){
        this.view = view;
    }

    @Override
    public List<Todo> getAllTodos() {
        //TODO : Appeller BD
        //Version statique pour creation affichage.
        List<Todo> l = new ArrayList<>();
        l.add(new Todo(0, "Liste A", Colors.GREEN, null));
        l.add(new Todo(1, "Liste B", Colors.BLUE, null));
        l.add(new Todo(2, "Liste C", Colors.GREY, null));
        l.add(new Todo(3, "Liste D", Colors.ORANGE, null));
        l.add(new Todo(4, "Liste E", Colors.PINK, null));
        l.add(new Todo(5, "Liste F", Colors.RED, null));
        l.add(new Todo(6, "Liste G", Colors.YELLOW, null));
        l.add(new Todo(7, "Liste H", Colors.GREEN, null));
        l.add(new Todo(8, "Liste I", Colors.BLUE, null));

        return l;
    }
}
