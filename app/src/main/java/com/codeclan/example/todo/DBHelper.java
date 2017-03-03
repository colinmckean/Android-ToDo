package com.codeclan.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //database version, needs changed if schema updates.
    public static final int DATABASE_VERSION = 1;

    //database name.
    public static final String DATABASE_NAME = "ToDoList.db";

    // name of tables.
    private static final String TABLE_TODO = "todos";
    // common field id.
    private static final String KEY_ID = "id";

    // column names
    private static final String KEY_TODO = "todo";
    private static final String KEY_STATUS = "status";

    //create to do table.
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TODO
            + " TEXT," + KEY_STATUS + ")";

    //constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //need to override onCreate takes a db and executes create table statement.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    //need to override on Upgrade, this takes the old version and drops the db, then calls the on onCreate method.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);

        // create new tables
        onCreate(db);
    }

    //Creating a new To Do task.
    public long createToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TODO, todo.getNote());
        values.put(KEY_STATUS, todo.getStatus());

        // insert row
        long todo_id = db.insert(TABLE_TODO, null, values);

        return todo_id;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;
        //get a database and cursor.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //check if rows, loop through rows adding to list
        if (cursor.moveToFirst())
            do {
                ToDo todo = new ToDo();
                todo.setId(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));

                // adding to to do list
                todos.add(todo);
            } while (cursor.moveToNext());
        //close the cursor and return the list of to do's
        cursor.close();
        return todos;
    }

    // get a to do by id.
    public ToDo getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // build string for SQL query.
        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
                + KEY_ID + " = " + todo_id;
        //create cursor.
        Cursor cursor = db.rawQuery(selectQuery, null);
        // check cursor is not null and then move to first.
        if (cursor != null)
            cursor.moveToFirst();

        ToDo todo = new ToDo();
        todo.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));
        cursor.close();
        return todo;
    }

    public int getToDoCount() {
        //create a string with SQL query
        String countQuery = "SELECT  * FROM " + TABLE_TODO;
        //get a db and cursor
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        //get the count from the cursor
        int count = cursor.getCount();

        //close cursor
        cursor.close();

        // returning the count
        return count;
    }

    public void deleteToDo(long idToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[]{String.valueOf(idToDelete)});
    }
}

