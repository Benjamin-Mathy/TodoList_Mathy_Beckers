package com.example.benja.todolist_mathy_beckers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;

import java.util.List;

/**
 * Created by Max on 29-04-17.
 */

public class TextAdapter extends BaseAdapter {

    private Context context;
    private List<Element> elements;

    public TextAdapter(Context context, List<Element> elements) {
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
        View v = inflater.inflate(R.layout.textelement_item, parent, false);
        EditText et = (EditText) v.findViewById(R.id.elementName);
        et.setText(elements.get(position).getText());
        v.setTag(elements.get(position));
        return v;
    }
}
