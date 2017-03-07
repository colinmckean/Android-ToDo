package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewToDoActivity extends AppCompatActivity {
    int listId;
    DBHelper db;
    ToDo toDoTosave;
    EditText toDoNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        db = DBHelper.getInstance(getApplicationContext());
        toDoNote = (EditText) findViewById(R.id.todoNote_EditText);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listId = extras.getInt("listid");
    }

    public void saveToDoToDB(View view) {
        toDoTosave = new ToDo(0, listId, toDoNote.getText().toString());
        Intent intent = new Intent(this, ListOfToDoItems.class);
        intent.putExtra("listId", listId);
        db.createToDo(toDoTosave);
        startActivity(intent);
    }
}
