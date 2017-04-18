package com.example.benja.todolist_mathy_beckers.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TodosAdapter;
import com.example.benja.todolist_mathy_beckers.presenter.IMainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private TodosAdapter adapter;
    private IMainPresenter presenter = new MainPresenter(this);

    private ListView todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alllist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        todos = (ListView) findViewById(R.id.todos);
        adapter = new TodosAdapter(this, presenter.getAllTodos());
        todos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
