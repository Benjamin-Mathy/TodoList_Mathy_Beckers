package com.example.benja.todolist_mathy_beckers.view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.adapter.ImageAdapter;
import com.example.benja.todolist_mathy_beckers.dataSource.ElementDAO;
import com.example.benja.todolist_mathy_beckers.dataSource.TodolistDAO;
import com.example.benja.todolist_mathy_beckers.model.ElementImage;
import com.example.benja.todolist_mathy_beckers.presenter.ITodoImagePresenter;
import com.example.benja.todolist_mathy_beckers.presenter.TodoImagePresenter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Max on 12-04-17.
 */
public class TodoImageActivity extends TodoActivity implements ITodoImageActivity {

    private ImageAdapter adapter;
    private ITodoImagePresenter presenter = new TodoImagePresenter(this, new TodolistDAO(this),new ElementDAO(this));

    private static final int SELECT_PICTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    private ListView elements;
    private RelativeLayout layout;

    private Uri newPicture;
    private String newPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_imagelist);
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
        elements = (ListView) findViewById(R.id.imageElements);
        adapter = new ImageAdapter(this, presenter.getAllElements());
        elements.setAdapter(adapter);
    }

    public void setBackgroundColor(){
        layout = (RelativeLayout) findViewById(R.id.TodoImageActivity);
        layout.setBackgroundColor(Color.parseColor(presenter.getColor().toString()));
    }

    @Override
    public void saveTitle(){
        presenter.setTitle(titleEdit.getText().toString());
    }

    public void saveTexts(){
        List<ElementImage> elList = new ArrayList();
        for (int i = elements.getChildCount() - 1 ; i>=0; i--) {
            ElementImage el = (ElementImage) elements.getItemAtPosition(i);
            EditText et = (EditText) elements.getChildAt(i).findViewById(R.id.elementName);
            el.setText(et.getText().toString());
            elList.add(el);
        }
        presenter.saveElements(elList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                presenter.addElement(presenter.getPath(data.getData()));
                onResume();
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                presenter.addElement(newPath);
                onResume();
            }
        }else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.cancel, Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "2DEW_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        newPath = image.getAbsolutePath();
        return image;
    }

    public void choosePicturePressed(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void takePicturePressed(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                newPicture = FileProvider.getUriForFile(this,
                        "com.example.benja.todolist_mathy_beckers.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, newPicture);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void removeElement(View view){
        final int position = elements.getPositionForView((View) view.getParent());
        presenter.removeElement(((ElementImage) elements.getItemAtPosition(position)));
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

    @Override
    public Context getContext() {
        return getContext();
    }
}
