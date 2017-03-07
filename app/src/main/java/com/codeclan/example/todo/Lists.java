package com.codeclan.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Lists extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listOfLists;
    DBHelper db;
    String[] names;
    List<ListName> allLists;
    String requestedListName;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        db = DBHelper.getInstance(getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newListButton();
            }
        });
        populateLists();
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

    public void newListButton() {

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
                populateLists();
            }
        });
        alert.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = allLists.get(i).getId();
        Log.d("List", "Whats been clicked.  " + id);
        Intent intent = new Intent(this, ListOfToDoItems.class);
        intent.putExtra("listId", id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "BACK BUTTON PRESSED!", Toast.LENGTH_SHORT).show();
    }

    public void populateLists() {
        allLists = db.getAllLists();
        names = getTitles(allLists);
        listOfLists = (ListView) findViewById(R.id.ListAllListNames_ListView);
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listOfLists.setAdapter(listAdapter);
        listOfLists.setOnItemClickListener(this);
    }

}
