package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TextAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.NotificationDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoTextPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoTextPresenter;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    private LinearLayout alarmMenu;
    private RelativeLayout layout;
    private EditText titleEdit;
    private Button openMenu;
    private TimePicker timePicker;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);
        menu = (LinearLayout) findViewById(R.id.settings_menu);
        openMenu = (Button) findViewById(R.id.parametersMenu);
        presenter.setTodoId(getIntent().getIntExtra("id", -1));

        alarmMenu = (LinearLayout) findViewById(R.id.settings_timeNotification);
        timePicker = (TimePicker) findViewById(R.id.tp_time);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker) findViewById(R.id.dp_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        titleEdit = (EditText) findViewById(R.id.listTitle);
        titleEdit.setText(presenter.getTitle());
        titleEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    saveTitle();
                }
            }
        });

        createList();
        setBackgroundColor();
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
        saveTitle();
    }

    public void createList(){
        elements = (ListView) findViewById(R.id.textElements);
        adapter = new TextAdapter(this, presenter.getAllElements());
        elements.setAdapter(adapter);
    }

    public void setBackgroundColor(){
        layout = (RelativeLayout) findViewById(R.id.TodoTextActivity);
        layout.setBackgroundColor(Color.parseColor(presenter.getColor().toString()));
    }

    public void saveTitle(){
        presenter.setTitle(titleEdit.getText().toString());
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
            openMenu.setBackground(getDrawable(R.drawable.round_button));
        }else{
            menu.setVisibility(View.VISIBLE);
            openMenu.setBackground(getDrawable(R.drawable.round_button_selected));
        }
    }

    public void chooseColor(View view){
        String tag = (String) view.getTag();
        presenter.setColor(tag);
        Toast.makeText(this, R.string.color_changed, Toast.LENGTH_SHORT).show();
        setBackgroundColor();
    }
    public void selectTimeAlarm(View view){
        alarmMenu.setVisibility(view.VISIBLE);
    }
    public void validateTimeNotfication(View view){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        GregorianCalendar calendar = new GregorianCalendar(year,month,day, hour, minute);

        presenter.addAlarm(this,calendar.getTimeInMillis());
        Toast.makeText(this.getBaseContext(), "Alarm is add successfully", Toast.LENGTH_SHORT).show();
        alarmMenu.setVisibility(view.INVISIBLE);
    }
    public void deleteNotification(View view){
        presenter.removeAlarm(this);
        Toast.makeText(this.getBaseContext(), "Alarm is deleted", Toast.LENGTH_SHORT).show();
    }
    public void cancelTimeNotification(View view){
        alarmMenu.setVisibility(view.INVISIBLE);
    }

    public void selectGpsLocation(View view) {
        //TODO  Open map activity
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);

        /*mGeofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId(entry.getKey())

                .setCircularRegion(
                        entry.getValue().latitude,
                        entry.getValue().longitude,
                        Constants.GEOFENCE_RADIUS_IN_METERS
                )
                .setExpirationDuration(SyncStateContract.Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());*/
    }
}
