package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DBHelper db;
    Button viewAllToDos;
    List<ToDo> inprogress;
    ListView ListOfToDos;
    String[] listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAllToDos = (Button) findViewById(R.id.viewAllToDos_Btn);
        db = DBHelper.getInstance(getApplicationContext());


        inprogress = db.getAllToDosByStatus(0);
        listItems = getTitles(inprogress);

        ListOfToDos = (ListView) findViewById(R.id.listByStatus_ListView);
//
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        ListOfToDos.setAdapter(listAdapter);
        ListOfToDos.setOnItemClickListener(this);
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

    public String[] getTitles(List<ToDo> allToDos) {
        String[] titles = new String[allToDos.size()];
        for (int i = 0; i < allToDos.size(); i++) {
            Log.d("get Titles", String.valueOf(i));
            titles[i] = allToDos.get(i).getNote();
            Log.d("get Titles", allToDos.get(i).getNote());
        }
        return titles;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("something", "has been selected");


    }
}

