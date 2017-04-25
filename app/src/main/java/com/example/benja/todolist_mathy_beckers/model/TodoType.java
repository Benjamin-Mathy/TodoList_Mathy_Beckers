package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 25-04-17.
 */

public enum TodoType {
    TEXT("TEXT"),
    SOUND("SOUND"),
    IMAGE("IMAGE");

    private final String todoType;

    TodoType(String todoType){
        this.todoType = todoType;
    }

    public String toString(){
        return this.todoType;
    }
}
