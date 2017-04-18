package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément textuel de todolist
 */
public class Element {

    private int id;
    private String text;

    public Element(String text){
        this.text = text;
    }

    public void editText(String text){
        this.text = text;
    }

    public int getId(){return this.id;}
}
