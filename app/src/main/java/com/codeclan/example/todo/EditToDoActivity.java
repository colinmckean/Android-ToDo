package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditToDoActivity extends AppCompatActivity {
    EditText editToDoNote;
    Button saveUpdatedToDoBtn;
    DBHelper db;
    ToDo todoToDisplay;
    ListName listName;
    String note;
    Spinner spinner;
    Spinner listsSpinner;
    List<String> list;
    List<String> listNames;
    ArrayAdapter<String> statusAdapter;
    ArrayAdapter<String> listNamesAdapter;
    List<ListName> allLists;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);
        db = DBHelper.getInstance(getApplicationContext());
        spinner = (Spinner) findViewById(R.id.toDoStatus_Spnr);
        listsSpinner = (Spinner) findViewById(R.id.toDoList_Spnr);
        list = new ArrayList<>();

        for (Status s : Status.values()) {
            list.add(s.toString());
        }

        listNames = new ArrayList<>();
        fillListNames();
        statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        listNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNames);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listNamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(statusAdapter);
        listsSpinner.setAdapter(listNamesAdapter);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("ToDoId");
        todoToDisplay = db.getTodo(id);
        listName = db.getListName(todoToDisplay.getListId());

        spinner.setSelection(todoToDisplay.getStatus() + 1);
        listsSpinner.setSelection(listNames.indexOf(listName.getList_name()));
        note = todoToDisplay.getNote();
        editToDoNote = (EditText) findViewById(R.id.editToDoNote_EditText);
        saveUpdatedToDoBtn = (Button) findViewById(R.id.saveUpdatedToDoToDB_Btn);
        editToDoNote.setText(note);
    }

    public void saveUpdatedToDoToDB(View view) {
        Intent intent = new Intent(this, ViewSingleToDo.class);
        todoToDisplay.setNote(editToDoNote.getText().toString());
        todoToDisplay.setStatus(spinner.getSelectedItemPosition() - 1);
        todoToDisplay.setListId(allLists.get(listsSpinner.getSelectedItemPosition()).getId());
        db.updateToDo(todoToDisplay);
        intent.putExtra("todo_id", id);
        startActivity(intent);
    }

    public void fillListNames() {
        allLists = db.getAllLists();
        for (ListName listName : allLists) {
            listNames.add(listName.getList_name());
            Log.d("listId", listName.getId() + " ");
        }

    }
}
