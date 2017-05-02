package com.example.benja.todolist_mathy_beckers.model;

import android.media.Image;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément de todolist de type image
 */
public class ElementImage extends Element {

    private String image;

    public ElementImage(){

    }

    public ElementImage(String text, int index, String image){
        super(text, index);
        this.image = image;
    }

    public void editImage(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new String(image);
    }
}
