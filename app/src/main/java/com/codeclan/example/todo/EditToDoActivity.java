package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EditToDoActivity extends AppCompatActivity {
    EditText editToDoNote;
    Button saveUpdatedToDoBtn;
    DBHelper db;
    ToDo todoToDisplay;
    String note;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);
        db = DBHelper.getInstance(getApplicationContext());
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("ToDoId");
        Log.d("ToDo", String.valueOf(id));

        todoToDisplay = db.getTodo(id);
        note = todoToDisplay.getNote();
        editToDoNote = (EditText) findViewById(R.id.editToDoNote_EditText);
        saveUpdatedToDoBtn = (Button) findViewById(R.id.saveUpdatedToDoToDB_Btn);
        editToDoNote.setText(note);
    }
}
