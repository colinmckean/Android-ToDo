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
    TextView toDoPriority;
    DBHelper db;
    ToDo todoToDisplay;
    long id;
    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_to_do);


        db = DBHelper.getInstance(getApplicationContext());
        toDoNote = (TextView) findViewById(R.id.taskNote_TextView);
        toDoPriority = (TextView) findViewById(R.id.taskPriority_TextView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("todo_id");
        Log.d("Single",id + " ..");
        todoToDisplay = db.getTodo(id);
        listId = todoToDisplay.getListId();
        Log.d("listId",String.valueOf(listId));
        toDoPriority.setText(todoToDisplay.getNote());
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
}
