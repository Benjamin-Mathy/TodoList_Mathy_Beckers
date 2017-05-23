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

    private long id;
    private TodoType type;
    private String name;
    private Colors color;
    private List<T> elements = new ArrayList<>();
    
    public Todo() {}

    public Todo(long id, TodoType type, String name, Colors color){
        this.id = id;
        this.type = type;
        this.name = name;
        this.color = color;
    }

    public long getId(){return this.id;}

    public String getName(){return this.name;}

    public Colors getColor(){return this.color;}

    public TodoType getType(){return this.type;}

    public void setId(long id) {
        this.id = id;
    }

    public void setType(TodoType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Colors color) {
        this.color = color;
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

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
