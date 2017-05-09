package com.example.benja.todolist_mathy_beckers.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.ImageAdapter;
import com.example.benja.todolist_mathy_beckers.adapter.TextAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoImagePresenter;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoTextPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoImagePresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoTextPresenter;

/**
 * Created by Max on 12-04-17.
 */

public class TodoImageActivity extends AppCompatActivity implements ITodoImageActivity {

    private ImageAdapter adapter;
    private ITodoImagePresenter presenter = new TodoImagePresenter(this, new ElementDAO(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
