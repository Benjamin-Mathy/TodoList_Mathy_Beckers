package com.example.benja.todolist_mathy_beckers.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;
import com.example.benja.todolist_mathy_beckers.model.Todo;

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
        TextView tv = (TextView)v.findViewById(R.id.todoName);
        tv.setText(elements.get(position).getText());
        v.setTag(elements.get(position));

        ImageView iv = (ImageView)v.findViewById(R.id.elementImage);
        iv.setImageURI(elements.get(position).getImage());
        return v;
    }
}
