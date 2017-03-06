package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSingleToDo extends AppCompatActivity {

    TextView toDoNote;
    TextView toDoStatus;
    TextView toDoListName;
    DBHelper db;
    ToDo todoToDisplay;
    ListName listName;
    long id;
    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_to_do);


        db = DBHelper.getInstance(getApplicationContext());
        toDoNote = (TextView) findViewById(R.id.taskNote_TextView);
        toDoStatus = (TextView) findViewById(R.id.taskPriority_TextView);
        toDoListName = (TextView) findViewById(R.id.taskListName_TextView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("todo_id");
        Log.d("Single",id + " ..");
        todoToDisplay = db.getTodo(id);
        listId = todoToDisplay.getListId();
        listName = db.getListName(listId);
        Log.d("listId",String.valueOf(listId));

        toDoNote.setText(todoToDisplay.getNote());

        //todoToDisplay.getStatus()
        Log.d("status",String.valueOf(Status.getStatus(todoToDisplay.getStatus())));
        toDoListName.setText(listName.getList_name());
        toDoStatus.setText(String.valueOf(Status.getStatus(todoToDisplay.getStatus())));
//        toDoNote.setText(todoToDisplay.getStatus());
    }


    public void deleteToDo(View view) {
        Intent intent = new Intent(this, ListOfToDoItems.class);
        intent.putExtra("listId",listId);
        db.deleteToDo(id);
        startActivity(intent);
    }

    //overriding back button to stop form landing on invalid activities.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListOfToDoItems.class);
        intent.putExtra("listId",listId);
        startActivity(intent);
        Toast.makeText(this, "BACK BUTTON PRESSED!", Toast.LENGTH_SHORT).show();
    }

    public void editToDo(View view) {
        Intent intent = new Intent(this, EditToDoActivity.class);
        intent.putExtra("ToDoId", id);
        startActivity(intent);
    }
}
