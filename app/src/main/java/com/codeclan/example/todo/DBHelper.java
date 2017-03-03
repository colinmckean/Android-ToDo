package com.codeclan.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
