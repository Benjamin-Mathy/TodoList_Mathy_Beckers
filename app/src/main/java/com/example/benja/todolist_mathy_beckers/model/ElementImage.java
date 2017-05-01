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

    private File image;

    public ElementImage(){

    }

    public ElementImage(String text, int index, File image){
        super(text, index);
        this.image = image;
    }

    public void editImage(File image){
        this.image = image;
    }

    public File getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new File(image);
    }
}
