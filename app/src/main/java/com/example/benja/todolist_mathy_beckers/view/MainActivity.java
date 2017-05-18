package com.example.benja.todolist_mathy_beckers.view;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
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
import com.google.firebase.messaging.RemoteMessage;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.provider.AlarmClock.EXTRA_RINGTONE;

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
                //TODO : Faire pour pouvoir utiliser les imageList déjà créées
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
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mNotifyBuilder;
// Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle("2Dew Test notif")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.cast_ic_notification_1)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        int numMessages = 0;
// Start of a loop that processes data and then notifies the user

        mNotifyBuilder.setContentText("coucou")
                .setNumber(++numMessages);
        // Because the ID remains unchanged, the existing notification is
        // updated.
        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());





        //TODO  Open map activity
        /*Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);*/
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
