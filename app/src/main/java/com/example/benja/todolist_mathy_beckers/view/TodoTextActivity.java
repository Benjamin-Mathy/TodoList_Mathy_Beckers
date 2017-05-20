package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TextAdapter;
import com.example.benja.todolist_mathy_beckers.adapter.TodosAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.NotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.presenter.IMainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoTextPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoTextPresenter;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Max on 12-04-17.
 */

public class TodoTextActivity extends AppCompatActivity implements ITodoTextActivity {

    private TextAdapter adapter;
    private ITodoTextPresenter presenter = new TodoTextPresenter(this, new TodolistDAO(this), new ElementDAO(this), new NotificationDAO(this));

    private ListView elements;
    private LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);
        menu = (LinearLayout) findViewById(R.id.settings_menu);

        presenter.setTodoId(getIntent().getIntExtra("id", -1));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        ActionBar actionBar = getSupportActionBar();

        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(248, 248, 248)));
        //rl.setBackgroundColor(Color.parseColor(todos.get(position).getColor().toString()));

        createList();
    }

    @Override
    public void onResume(){
        super.onResume();
        createList();
    }

    @Override
    public void onStop(){
        super.onStop();
        saveTexts();
    }

    public void createList(){
        elements = (ListView) findViewById(R.id.textElements);
        adapter = new TextAdapter(this, presenter.getAllElements());
        elements.setAdapter(adapter);
    }

    public void saveTexts(){
        List<Element> elList = new ArrayList();
        for (int i = elements.getChildCount() - 1 ; i>=0; i--) {
            Element el = (Element) elements.getItemAtPosition(i);
            EditText et = (EditText) elements.getChildAt(i).findViewById(R.id.elementName);
            el.setText(et.getText().toString());
            elList.add(el);
        }
        presenter.saveElements(elList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void newElementTextList(View view){
        saveTexts();
        presenter.addElement();
        onResume();
    }

    public void removeElement(View view){
        final int position = elements.getPositionForView((View) view.getParent());
        presenter.removeElement(((Element)elements.getItemAtPosition(position)));
        onResume();
    }

    public void openMenu(View view) {
        if(menu.getVisibility() == View.VISIBLE){
            menu.setVisibility(View.INVISIBLE);
        }else{
            menu.setVisibility(View.VISIBLE);
        }
    }
}
