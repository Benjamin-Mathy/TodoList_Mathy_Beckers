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

    private int id;
    private String name;
    private Colors color;
    private List<T> elements = new ArrayList<>();
    //private Notification notification ?

    public Todo(int id, String name, Colors color, List<T> elements){
        this.id = id;
        this.name = name;
        this.color = color;
        this.elements = elements;
    }

    public int getId(){return this.id;}

    public String getName(){return this.name;}

    public Colors getColor(){return this.color;}

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
