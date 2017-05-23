package com.example.benja.todolist_mathy_beckers.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.benja.todolist_mathy_beckers.R;

import java.util.GregorianCalendar;

/**
 * Created by Max on 23-05-17.
 */

public abstract class TodoActivity extends AppCompatActivity {

    private LinearLayout menu;
    private Button openMenu;

    protected EditText titleEdit;

    private LinearLayout alarmMenu;
    private TimePicker timePicker;
    private DatePicker datePicker;
    protected GregorianCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menu = (LinearLayout) findViewById(R.id.settings_menu);
        openMenu = (Button) findViewById(R.id.parametersMenu);

        alarmMenu = (LinearLayout) findViewById(R.id.settings_timeNotification);
        timePicker = (TimePicker) findViewById(R.id.tp_time);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker) findViewById(R.id.dp_date);

        titleEdit = (EditText) findViewById(R.id.listTitle);

        titleEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    saveTitle();
                }
            }
        });
    }

    public abstract void saveTitle();

    public void openMenu(View view) {
        if(menu.getVisibility() == View.VISIBLE){
            menu.setVisibility(View.INVISIBLE);
            openMenu.setBackground(getDrawable(R.drawable.round_button));
        }else{
            menu.setVisibility(View.VISIBLE);
            openMenu.setBackground(getDrawable(R.drawable.round_button_selected));
        }
    }

    public void selectTimeAlarm(View view){
        alarmMenu.setVisibility(view.VISIBLE);
    }

    public void validateTimeNotification(View view){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        calendar = new GregorianCalendar(year,month,day, hour, minute);

        alarmMenu.setVisibility(view.INVISIBLE);
    }

    public void closeSettingsNotification(View view){
        alarmMenu.setVisibility(view.INVISIBLE);
    }
}
