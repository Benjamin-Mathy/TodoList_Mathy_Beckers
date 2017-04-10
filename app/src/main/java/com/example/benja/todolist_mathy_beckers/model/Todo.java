package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 10-04-17.
 */

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une liste de choses à faire
 */
public class Todo<T extends Element>{

    private String name;
    private Color color;
    private List<T> elements = new ArrayList<>();
    //private Notification notification ?

    public Todo(String name, Color color, List<T> elements){
        this.name = name;
        this.color = color;
        this.elements = elements;
    }

    public void rename(String name){
        this.name = name;
    }

    public void add(T element){
        elements.add(element);
    }

    public void delete(T element){
        elements.remove(element);
    }

    public List<T> getElements(){
        return elements;
    }

    public T getElement(int index){
        return elements.get(index);
    }
}
