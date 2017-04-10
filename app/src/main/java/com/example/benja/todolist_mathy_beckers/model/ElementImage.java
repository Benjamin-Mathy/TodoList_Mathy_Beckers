package com.example.benja.todolist_mathy_beckers.model;

import android.media.Image;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément de todolist de type image
 */
public class ElementImage extends Element {

    private Image image;

    public ElementImage(String text, Image image){
        super(text);
        this.image = image;
    }

    public void editImage(Image image){
        this.image = image;
    }

}