package com.example.benja.todolist_mathy_beckers.model;

import android.media.Image;
import android.net.Uri;

/**
 * Created by Max on 10-04-17.
 */

/**
 * Classe représentant un élément de todolist de type image
 */
public class ElementImage extends Element {

    private Uri imageUri;

    public ElementImage(String text, int index, Uri imageUri){
        super(text, index);
        this.imageUri = imageUri;
    }

    public void editImage(Uri imageUri){
        this.imageUri = imageUri;
    }

    public Uri getImage() {
        return imageUri;
    }
}
