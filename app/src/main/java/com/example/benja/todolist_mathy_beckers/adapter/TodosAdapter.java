package com.example.benja.todolist_mathy_beckers.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.model.Todo;

import java.util.List;

/**
 * Created by Max on 13-04-17.
 */

public class TodosAdapter extends BaseAdapter {

    private Context context;
    private List<Todo> todos;

    public TodosAdapter(Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.todo_item, parent, false);
        TextView tv = (TextView)v.findViewById(R.id.todoName);
        tv.setText(todos.get(position).getName());
        //TODO : P-e ici aussi pour mettre la couleur de la liste ?
        v.setTag(todos.get(position));

        LinearLayout rl = (LinearLayout)v.findViewById(R.id.todoItem);
        rl.setBackgroundColor(Color.parseColor(todos.get(position).getColor().toString()));
        return v;
    }
}
