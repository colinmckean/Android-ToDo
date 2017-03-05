package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    Button viewAllToDos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAllToDos = (Button) findViewById(R.id.viewAllToDos_Btn);


        db = DBHelper.getInstance(getApplicationContext());
//        ListName list1 = new ListName("Make more lists");
//        ToDo todo1 = new ToDo("Create a Sample; Task", 1);
//ToDo todo2 = new ToDo(-1, 2, "more to do");
//ToDo toDo3 = new ToDo(-1, 2, "running out of idea");
//        ToDo todo3 = new ToDo(1, "something");
//        db.createList(list1);
//        db.createToDo(todo1);
//        db.createToDo(todo2);
//        db.createToDo(toDo3);
//        db.deleteToDo(24);
//        todoToUpDate.setNote("THIS MUST BE SOMETHING AGAIN");
//        todoToUpDate.setStatus(4);
//        db.updateToDo(todoToUpDate);
//        ToDo todoToUpDate = db.getTodo(2);

//        Log.e("ToDo: ", "FRESH HOT OUTPUT");
//        Log.e("Todo: ", "Found: " + db.getToDoCount() + " ToDo's");
        List<ToDo> allLists = db.getAllToDos();
        for (ToDo todo : allLists) {
            Log.d("ToDo", todo.getNote() + " id: " + String.valueOf(todo.getId()) + "  " + todo.getStatus() + " " + todo.getListId());
        }

        //lets print out the list names
//        Log.e("List", "**** printing out list names");
//        ListName listNameToUpdate = db.getListName(1);
//        listNameToUpdate.setList_name("UPDATEDS!");
//        db.updateList(listNameToUpdate);
//        List<ListName> allLists = db.getAllLists();
//
//        for(ListName listName : allLists){
//            Log.d("List", "id" + listName.getId() + listName.getList_name());
//        }


//        ToDo todoToUpDate = db.getTodo(12);
//         Log.d("ToDo Found", todoToUpDate.getNote() + " " + todoToUpDate.getStatus());
//        todoToUpDate.setStatus(0);
//        db.updateToDo(todoToUpDate);
//        Log.d("ToDo Found", todoToUpDate.getNote() + " " + todoToUpDate.getStatus());
    }

    public void viewLists(View view) {
        Intent intent = new Intent(this, ListAllListsActivity.class);
        startActivity(intent);
    }



}

