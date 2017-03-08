package com.codeclan.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DBHelper db;
    Button viewAllToDos;
    List<ToDo> inprogress;
    ListView ListOfToDos;
    String[] listItems;
    ArrayAdapter<String> listAdapter;
    TextView heading;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAllToDos = (Button) findViewById(R.id.viewAllToDos_Btn);
        heading = (TextView) findViewById(R.id.mainActivityHeading_TextView);
        db = DBHelper.getInstance(getApplicationContext());
        populateList(0);
    }

    public void viewLists(View view) {
        Intent intent = new Intent(this, Lists.class);
        startActivity(intent);
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

        long id = inprogress.get(i).getId();
        Intent intent = new Intent(this, ViewSingleToDo.class);
        intent.putExtra("todo_id", id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.todo:
                heading.setText(R.string.to_do);
                populateList(0);
                return true;
            case R.id.inprogress:
                heading.setText(R.string.in_progress);
                populateList(1);
                return true;
            case R.id.done:
                heading.setText(R.string.done);
                populateList(-1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void populateList(int status) {
        inprogress = db.getAllToDosByStatus(status);
        listItems = getTitles(inprogress);
        ListOfToDos = (ListView) findViewById(R.id.listByStatus_ListView);
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        ListOfToDos.setAdapter(listAdapter);
        ListOfToDos.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        heading.setText(R.string.to_do);
        populateList(0);
    }
}

