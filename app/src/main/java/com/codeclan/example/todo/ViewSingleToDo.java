package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewSingleToDo extends AppCompatActivity {

    TextView toDoNote;
    TextView toDoPriority;
    DBHelper db;
    ToDo todoToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_to_do);


        db = DBHelper.getInstance(getApplicationContext());
        toDoNote = (TextView) findViewById(R.id.taskNote_TextView);
        toDoPriority = (TextView) findViewById(R.id.taskPriority_TextView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        long id = extras.getLong("todo_id");
        Log.d("Single",id + " ..");
        todoToDisplay = db.getTodo(id);
        toDoPriority.setText(todoToDisplay.getNote());
//        toDoNote.setText(todoToDisplay.getStatus());
    }
}
