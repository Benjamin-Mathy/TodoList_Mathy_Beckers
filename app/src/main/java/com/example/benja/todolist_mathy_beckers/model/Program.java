package com.example.benja.todolist_mathy_beckers.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Max on 10-04-17.
 */

public class Program {

    Collection<Todo> todos;

    public void addTextTodo(){
        todos.add(new Todo("Nouvelle liste", null, new ArrayList<Element>()));
    }

    public void addImageTodo(){
        todos.add(new Todo("Nouvelle liste", null, new ArrayList<ElementImage>()));
    }
}
