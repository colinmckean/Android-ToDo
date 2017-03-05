package com.codeclan.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewListActivity extends AppCompatActivity {
    Button saveListToDB;
    EditText listName;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        db = DBHelper.getInstance(getApplicationContext());
        saveListToDB = (Button) findViewById(R.id.addNewList_Btn);
        listName = (EditText) findViewById(R.id.listname_EditText);
    }

    public void saveListToDB(View view){
        String listname =  listName.getText().toString();
        ListName listTosave = new ListName(listname);
        Intent intent = new Intent(this, ListAllListsActivity.class);
        db.createList(listTosave);
        startActivity(intent);
    }
}
