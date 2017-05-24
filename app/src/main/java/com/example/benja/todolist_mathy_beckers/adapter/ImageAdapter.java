package com.example.benja.todolist_mathy_beckers.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Max on 29-04-17.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<ElementImage> elements;

    public ImageAdapter(Context context, List<ElementImage> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return elements.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.imageelement_item, parent, false);
        EditText et = (EditText) v.findViewById(R.id.elementName);
        et.setText(elements.get(position).getText());

        ImageView iv = (ImageView)v.findViewById(R.id.elementImage);

        //Utilisation de Picasso pour charger les images dans des threads séparés et les redimensioner.
        Picasso.with(context)
                .load("file://" + Uri.parse(elements.get(position).getImage())).fit().centerInside()
                .into(iv);

        v.setTag(elements.get(position));
        return v;
    }
}
