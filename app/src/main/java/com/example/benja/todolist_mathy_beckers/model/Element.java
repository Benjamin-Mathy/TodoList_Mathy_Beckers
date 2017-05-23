package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément textuel de todolist
 */
public class Element implements Comparable<Element>{

    private long id;
    private String text;
    private int index;

    public Element(){}

    public Element(String text, int index){
        this.text = text;
        this.index = index;
    }

    public long getId(){return this.id;}

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Element element){
        if(this.getIndex() > element.getIndex()){
            return 1;
        }else if(this.getIndex() < element.getIndex()){
            return -1;
        }
        return 0;
    }
}
