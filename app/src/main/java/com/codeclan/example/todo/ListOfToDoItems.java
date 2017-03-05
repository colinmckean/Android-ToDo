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
import android.widget.Toast;

import java.util.List;

public class ListOfToDoItems extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DBHelper db;
    List<ToDo> allToDosInList;
    ListView ListOfToDos;
    String[] listItems;
    Button newToDo;
    Button deleteListBtn;
    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_to_do_items);
        db = DBHelper.getInstance(getApplicationContext());
        newToDo = (Button) findViewById(R.id.addNewToDo_Btn);
        deleteListBtn = (Button) findViewById(R.id.deleteList_Btn);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listId = extras.getInt("listId");
        Log.d("listId", String.valueOf(listId));
        allToDosInList = db.getAllToDosInAList(listId);
        listItems = getTitles(allToDosInList);

        if (listItems.length > 0) {
            deleteListBtn.setVisibility(View.GONE);
        } else {
            deleteListBtn.setVisibility(View.VISIBLE);

        }


        for (ToDo todo : allToDosInList) {
            Log.d("ToDo", todo.getNote() + " id: " + String.valueOf(todo.getId()) + "  " + todo.getStatus() + " " + todo.getListId());
        }

        ListOfToDos = (ListView) findViewById(R.id.listAllToDos_ListView);
//
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        ListOfToDos.setAdapter(listAdapter);
        ListOfToDos.setOnItemClickListener(this);
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
        long id = allToDosInList.get(i).getId();
        Intent intent = new Intent(this, ViewSingleToDo.class);
        intent.putExtra("todo_id", id);

        startActivity(intent);

        Log.d("ToDo", "clicked a todo with ID;" + id);
    }

    public void goToAddNewToDoActivity(View view) {
        Intent intent = new Intent(this, NewToDoActivity.class);
        intent.putExtra("listid", listId);
        startActivity(intent);
    }

    //overriding back button to stop form landing on invalid activities.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListAllListsActivity.class);
        startActivity(intent);
        Toast.makeText(this, "BACK BUTTON PRESSED!", Toast.LENGTH_SHORT).show();
    }

    public void deleteList(View view) {
        Intent intent = new Intent(this, ListAllListsActivity.class);
        db.deleteList(db.getListName(listId));
        startActivity(intent);
    }
}
