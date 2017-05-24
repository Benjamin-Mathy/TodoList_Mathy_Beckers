package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.TextAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.Element;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoTextPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoTextPresenter;
import java.util.ArrayList;
import java.util.List;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Max on 12-04-17.
 */
public class TodoTextActivity extends TodoActivity implements ITodoTextActivity {

    private TextAdapter adapter;
    private ITodoTextPresenter presenter = new TodoTextPresenter(this, new TodolistDAO(this), new ElementDAO(this));

    private ListView elements;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_textlist);
        super.onCreate(savedInstanceState);
        presenter.setTodoId(getIntent().getIntExtra("id", -1));

        titleEdit.setText(presenter.getTitle());

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

    @Override
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

    public void chooseColor(View view){
        String tag = (String) view.getTag();
        presenter.setColor(tag);
        Toast.makeText(this, R.string.color_changed, Toast.LENGTH_SHORT).show();
        setBackgroundColor();
    }

    @Override
    public void validateTimeNotification(View view){
        super.validateTimeNotification(view);
        presenter.addAlarm(this,calendar.getTimeInMillis());
        Toast.makeText(this.getBaseContext(), R.string.alarm_add, Toast.LENGTH_SHORT).show();
    }

    public void selectGpsLocation(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }

    public void deleteNotification(View view){
        presenter.removeAlarm(this);
        Toast.makeText(this.getBaseContext(), R.string.alarm_del, Toast.LENGTH_SHORT).show();
    }
}
