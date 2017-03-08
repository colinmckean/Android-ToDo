package com.codeclan.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListToDoItems extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DBHelper db;
    private List<ToDo> allToDosInList;
    private ListView ListOfToDos;
    private String[] listItems;
    private Button newToDo;
    private Button deleteListBtn;
    private int listId;
    private ToDo toDoTosave;
    private String toDoNote;
    private String listName;
    private TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do_items);


        db = DBHelper.getInstance(getApplicationContext());
        heading = (TextView) findViewById(R.id.listHeader);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listId = extras.getInt("listId");
        heading.setText(db.getListName(listId).getList_name().toString());
        populateLists();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddNewToDoActivity(view);

            }
        });
    }


    public String[] getTitles(List<ToDo> allToDos) {
        String[] titles = new String[allToDos.size()];
        for (int i = 0; i < allToDos.size(); i++) {
            titles[i] = allToDos.get(i).getNote();
        }
        return titles;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        long id = allToDosInList.get(i).getId();
        Intent intent = new Intent(this, ViewSingleToDo.class);
        intent.putExtra("todo_id", id);
        startActivity(intent);
    }
    public void goToAddNewToDoActivity(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Enter a Task");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Save ToDo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                toDoNote = input.getText().toString();
                toDoTosave = new ToDo(0, listId, toDoNote);
                db.createToDo(toDoTosave);
                populateLists();
                Snackbar.make(getCurrentFocus(), toDoNote + " has been added!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        alert.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "BACK BUTTON PRESSED!", Toast.LENGTH_SHORT).show();
    }

    public void deleteList(View view) {
        Intent intent = new Intent(this, Lists.class);
        db.deleteList(db.getListName(listId));
        startActivity(intent);
    }
    public void populateLists() {
        deleteListBtn = (Button) findViewById(R.id.deleteList_Btn);
        allToDosInList = db.getAllToDosInAList(listId);
        listItems = getTitles(allToDosInList);

        if (listItems.length > 0) {
            deleteListBtn.setVisibility(View.GONE);
        } else {
            deleteListBtn.setVisibility(View.VISIBLE);
        }
        ListOfToDos = (ListView) findViewById(R.id.listAllToDos_ListView);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        ListOfToDos.setAdapter(listAdapter);
        ListOfToDos.setOnItemClickListener(this);
    }

}
