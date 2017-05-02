package com.example.benja.todolist_mathy_beckers.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max on 13-04-17.
 */

public enum Colors {
    RED("#E57373"),
    GREEN("#81C784"),
    YELLOW("#FFF176"),
    BLUE("#64B5F6"),
    ORANGE("#FFB74D"),
    GREY("#B0BEC5"),
    PINK("#F48FB1");

    private final String color;

    Colors(String color){
        this.color = color;
    }

    public String toString(){
        return this.color;
    }

    public static Colors valueFor(String text) {
        for (Colors color : values()) {
            if (color.toString().equals(text)) {
                return color;
            }
        }
        return null;
    }
}
