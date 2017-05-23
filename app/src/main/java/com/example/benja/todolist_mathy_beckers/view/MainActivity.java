package com.example.benja.todolist_mathy_beckers.view;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.benja.todolist_mathy_beckers.Manifest;
import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TodosAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.NotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Todo;
import com.example.benja.todolist_mathy_beckers.model.TodoType;
import com.example.benja.todolist_mathy_beckers.presenter.IMainPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MainPresenter;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.provider.AlarmClock.EXTRA_RINGTONE;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private TodosAdapter adapter;
    private IMainPresenter presenter = new MainPresenter(this, new TodolistDAO(this), new ElementDAO(this), new NotificationDAO(this));

    private ListView todos;
    private EditText searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alllist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        askAllPermissions();
        createList(presenter.getAllTodos());
        setSearchfield();
    }

    public void askAllPermissions(){
        List<String> permissions = new ArrayList();
        testPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, permissions);
        testPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, permissions);
        testPermission(android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, permissions);
        testPermission(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY, permissions);
        testPermission(android.Manifest.permission.CAMERA, permissions);
        testPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, permissions);
        testPermission(android.Manifest.permission.INTERNET, permissions);
        testPermission(android.Manifest.permission.ACCESS_NETWORK_STATE, permissions);
        int result = 0;
        if(!permissions.isEmpty()){
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[0]), result);
        }
    }

    private void testPermission(String permission, List<String> permissions){
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            permissions.add(permission);
        }
    }

    public void createList(List<Todo> todolists){
        todos = (ListView) findViewById(R.id.todos);
        adapter = new TodosAdapter(this, todolists);
        todos.setAdapter(adapter);

        todos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idTodo = (int) ((Todo)parent.getItemAtPosition(position)).getId();
                TodoType type = presenter.getTypeOfTodo(idTodo);
                switch (type){
                    case TEXT:
                        createIntentForText(idTodo);
                        break;
                    case IMAGE:
                        createIntentForImage(idTodo);
                        break;
                }

            }
        });
    }

    private void setSearchfield() {
        searchField = (EditText) findViewById(R.id.searchField);
        searchField.setText("");
    }

    @Override
    public void onResume(){
        super.onResume();
        createList(presenter.getAllTodos());
        setSearchfield();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void soundButtonclicked(View view){
        Toast.makeText(this, R.string.not_available, Toast.LENGTH_SHORT).show();
    }

    public void newTextList(View view){
        presenter.addTodo(TodoType.TEXT);
        Intent intent = new Intent(this, TodoTextActivity.class);
        intent.putExtra("id", presenter.getLastTodoId());
        startActivity(intent);
    }

    public void createIntentForText(long id){
        Intent intent = new Intent(this, TodoTextActivity.class);
        intent.putExtra("id", (int)id);
        startActivity(intent);
    }

    public void newImageList(View view){
        presenter.addTodo(TodoType.IMAGE);
        Intent intent = new Intent(this, TodoImageActivity.class);
        intent.putExtra("id", presenter.getLastTodoId());
        startActivity(intent);
    }

    public void createIntentForImage(long id){
        Intent intent = new Intent(this, TodoImageActivity.class);
        intent.putExtra("id", (int)id);
        startActivity(intent);
    }

    public void removeTodo(View view){
        final int position = todos.getPositionForView((View) view.getParent());
        presenter.removeTodo(((Todo)todos.getItemAtPosition(position)));
        onResume();
    }

    public void processSearch(MenuItem item) {
        if(searchField.getText().toString().length() < 3){
            createList(presenter.getAllTodos());
            setSearchfield();
        }else {
            createList(presenter.getTodosWith(searchField.getText().toString()));
        }
    }
}
