package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditToDoActivity extends AppCompatActivity {
    EditText editToDoNote;
    Button saveUpdatedToDoBtn;
    DBHelper db;
    ToDo todoToDisplay;
    String note;
    Spinner spinner;
    long id;
    List<String> list;
    ArrayAdapter<String> statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);
        db = DBHelper.getInstance(getApplicationContext());
        spinner = (Spinner) findViewById(R.id.toDoStatus_Spnr);


        //this needs refactoed, possibly a enum.
        list = new ArrayList<>();
        list.add("Done");
        list.add("To Do");
        list.add("In Progress");

        statusAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);


        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(statusAdapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("ToDoId");
        Log.d("ToDo", String.valueOf(id));

        todoToDisplay = db.getTodo(id);
        //plan on having done has -1, to do 0, in progress 1 will look at enums.
        spinner.setSelection(todoToDisplay.getStatus() + 1);
        note = todoToDisplay.getNote();
        editToDoNote = (EditText) findViewById(R.id.editToDoNote_EditText);
        saveUpdatedToDoBtn = (Button) findViewById(R.id.saveUpdatedToDoToDB_Btn);
        editToDoNote.setText(note);
    }

    public void saveUpdatedToDoToDB(View view) {
        Intent intent = new Intent(this, ViewSingleToDo.class);
        todoToDisplay.setNote(editToDoNote.getText().toString());
        //using -1 to update database to maintain -1, 0, 1 values.
        todoToDisplay.setStatus(spinner.getSelectedItemPosition() - 1);
        db.updateToDo(todoToDisplay);
        intent.putExtra("todo_id", id);
        startActivity(intent);
    }
}
