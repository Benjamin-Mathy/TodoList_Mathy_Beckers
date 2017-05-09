package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TextAdapter;
import com.example.benja.todolist_mathy_beckers.adapter.TodosAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.presenter.IMainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoTextPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoTextPresenter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Max on 12-04-17.
 */

public class TodoTextActivity extends AppCompatActivity implements ITodoTextActivity {

    private TextAdapter adapter;
    private ITodoTextPresenter presenter = new TodoTextPresenter(this, new ElementDAO(this));

    private ListView elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);

        Intent i = getIntent();
        int id = getIntent().getIntExtra("id", -1);
        presenter.setTodoId(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        ActionBar actionBar = getSupportActionBar();

        createList();
    }

    public void createList(){
        elements = (ListView) findViewById(R.id.textElements);
        adapter = new TextAdapter(this, presenter.getAllElements());
        elements.setAdapter(adapter);

        elements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
