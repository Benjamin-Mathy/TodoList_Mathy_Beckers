package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TodosAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.NotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.presenter.IMainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MainPresenter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private TodosAdapter adapter;
    private IMainPresenter presenter = new MainPresenter(this, new TodolistDAO(this), new ElementDAO(this), new NotificationDAO(this));

    private ListView todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alllist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        createList();
    }

    public void createList(){
        todos = (ListView) findViewById(R.id.todos);
        adapter = new TodosAdapter(this, presenter.getAllTodos());
        todos.setAdapter(adapter);

        todos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createIntentForText(((Todo)parent.getItemAtPosition(position)).getId());
            }
        });
    }

    public void createIntentForText(long id){
        Intent intent = new Intent(this, TodoTextActivity.class);
        intent.putExtra("id", (int)id);
        startActivity(intent);

    }

    @Override
    public void onResume(){
        super.onResume();
        createList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void soundButtonclicked(View view){
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }

    public void newTextList(View view){
        presenter.addTodo(TodoType.TEXT);
        Intent intent = new Intent(this, TodoTextActivity.class);
        intent.putExtra("id", presenter.getLastTodoId());
        startActivity(intent);
    }

    public void newImageList(View view){
        presenter.addTodo(TodoType.IMAGE);
        Intent intent = new Intent(this, TodoImageActivity.class);
        intent.putExtra("id", presenter.getLastTodoId());
        startActivity(intent);
    }

    public void removeTodo(View view){
        final int position = todos.getPositionForView((View) view.getParent());
        presenter.removeTodo(((Todo)todos.getItemAtPosition(position)));
        onResume();
    }


}
