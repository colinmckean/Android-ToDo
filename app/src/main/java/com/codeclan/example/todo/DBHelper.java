package com.codeclan.example.todo;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 03/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

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
}
