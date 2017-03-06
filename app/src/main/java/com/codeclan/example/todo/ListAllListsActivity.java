package com.codeclan.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListAllListsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listOfLists;
    DBHelper db;
    String[] names;
    List<ListName> allLists;
    Button newListBtn;
    String requestedListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_lists);
        newListBtn = (Button) findViewById(R.id.addNewList_Btn);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        db = DBHelper.getInstance(getApplicationContext());
//        setSupportActionBar(toolbar);
        allLists = db.getAllLists();
        names = getTitles(allLists);
        listOfLists = (ListView) findViewById(R.id.ListAllListNames_ListView);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listOfLists.setAdapter(listAdapter);
        listOfLists.setOnItemClickListener(this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public String[] getTitles(List<ListName> allToDos) {
        String[] titles = new String[allToDos.size()];
        for (int i = 0; i < allToDos.size(); i++) {
            Log.d("get Titles", String.valueOf(i));
            titles[i] = allToDos.get(i).getList_name();
            Log.d("get Titles", allToDos.get(i).getList_name());
        }
        return titles;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = allLists.get(i).getId();
        Log.d("List", "Whats been clicked.  " + id);

        Intent intent = new Intent(this, ListOfToDoItems.class);
        intent.putExtra("listId", id);
        startActivity(intent);
    }

    public void newListButton(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Enter a ListName");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Save List", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                requestedListName = input.getText().toString();
                ListName listTosave = new ListName(requestedListName);
                Log.d("test", requestedListName);
                db.createList(listTosave);
                Intent intent = new Intent(getApplicationContext(), ListAllListsActivity.class);
                startActivity(intent);

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
}


