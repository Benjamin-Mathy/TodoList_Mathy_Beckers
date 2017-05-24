package com.example.benja.todolist_mathy_beckers.model;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément de todolist de type image
 */
public class ElementImage extends Element {

    private String image;

    public ElementImage(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new String(image);
    }
}
