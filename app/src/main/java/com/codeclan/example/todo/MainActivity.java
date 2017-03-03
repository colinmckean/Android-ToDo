package com.codeclan.example.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(getApplicationContext());
        ToDo todo1 = new ToDo("Create a Sample Task", 0);
        ToDo todo2 = new ToDo("Create more Tasks", 0);
        db.createToDo(todo1);
        db.createToDo(todo2);
        db.deleteToDo(2);

        Log.d("ToDo: ", "FRESH HOT OUTPUT");
        Log.e("Todo: ", "Found: " + db.getToDoCount() + " ToDo's");
        List<ToDo> allToDos = db.getAllToDos();
        for (ToDo todo : allToDos) {
            Log.d("ToDo", todo.getNote() + " id: " + String.valueOf(todo.getId()) + "  " + String.valueOf(todo.getStatus()));
        }

    }
}
