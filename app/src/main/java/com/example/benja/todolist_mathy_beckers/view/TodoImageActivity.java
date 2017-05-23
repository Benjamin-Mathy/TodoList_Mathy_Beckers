package com.example.benja.todolist_mathy_beckers.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

/**
 * Created by Max on 12-04-17.
 */

public class TodoImageActivity extends AppCompatActivity implements ITodoImageActivity {

    private ImageAdapter adapter;
    private ITodoImagePresenter presenter = new TodoImagePresenter(this, new TodolistDAO(this),new ElementDAO(this));

    private static final int SELECT_PICTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    private ListView elements;
    private LinearLayout menu;
    private RelativeLayout layout;
    private EditText titleEdit;
    private Uri newPicture;
    private String newPath;
    private Button openMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);
        menu = (LinearLayout) findViewById(R.id.settings_menu);
        openMenu = (Button) findViewById(R.id.parametersMenu);
        presenter.setTodoId(getIntent().getIntExtra("id", -1));

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
        elements = (ListView) findViewById(R.id.imageElements);
        adapter = new ImageAdapter(this, presenter.getAllElements());
        elements.setAdapter(adapter);
    }

    public void setBackgroundColor(){
        layout = (RelativeLayout) findViewById(R.id.TodoImageActivity);
        layout.setBackgroundColor(Color.parseColor(presenter.getColor().toString()));
    }

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
                presenter.addElement(getPath(data.getData()));
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

    public String getPath(Uri uri){
        String wholeID = DocumentsContract.getDocumentId(uri);
        String id = wholeID.split(":")[1];
        String[] column = { MediaStore.Images.Media.DATA };

        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{ id }, null);
        String filePath = "";
        int columnIndex = 0;
        if (cursor != null) {
            columnIndex = cursor.getColumnIndex(column[0]);
        }
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();

        return filePath;
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

    public void selectGpsLocation(View view) {}
}
