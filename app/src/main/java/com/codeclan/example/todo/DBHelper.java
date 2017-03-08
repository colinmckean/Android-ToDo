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
    private static DBHelper instance = null;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoList.db";
    private static final String TABLE_TODO = "todos";
    private static final String TABLE_LISTNAMES = "list_names";
    private static final String KEY_ID = "id";
    private static final String KEY_TODO = "todo";
    private static final String KEY_STATUS = "status";
    private static final String KEY_LISTNAME = "list_name";
    private static final String KEY_LISTID = "list_id";
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TODO
            + " TEXT," + KEY_STATUS + ", " + KEY_LISTID + ")";
    private static final String CREATE_TABLE_LISTNAMES = "CREATE TABLE " +
            TABLE_LISTNAMES + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_LISTNAME + " TEXT"
            + ")";

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_LISTNAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTNAMES);
        onCreate(db);
    }

    public long createToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TODO, todo.getNote());
        values.put(KEY_STATUS, todo.getStatus());
        values.put(KEY_LISTID, todo.getListId());
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
        String selectQuery = "SELECT * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
            do {
                ToDo todo = new ToDo();
                todo.setId(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));
                todo.setStatus(cursor.getInt((cursor.getColumnIndex(KEY_STATUS))));
                todo.setListId(cursor.getInt((cursor.getColumnIndex(KEY_LISTID))));
                todos.add(todo);
            } while (cursor.moveToNext());
        cursor.close();
        return todos;
    }

    public ToDo getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE " + KEY_ID + " = " + todo_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDo todo = new ToDo();
        todo.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));
        todo.setStatus(cursor.getInt((cursor.getColumnIndex(KEY_STATUS))));
        todo.setListId(cursor.getInt((cursor.getColumnIndex(KEY_LISTID))));
        cursor.close();
        return todo;
    }

    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteToDo(long idToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[]{String.valueOf(idToDelete)});
    }

    public int updateToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TODO, todo.getNote());
        values.put(KEY_STATUS, todo.getStatus());
        values.put(KEY_LISTID, todo.getListId());
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
    }

    public List<ToDo> getAllToDosInAList(int listId) {
        List<ToDo> todos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TODO + " WHERE " + KEY_LISTID + " = " + listId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ToDo todo = new ToDo();
                todo.setId(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));
                todo.setListId(cursor.getInt((cursor.getColumnIndex(KEY_LISTID))));
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return todos;
    }


    public List<ToDo> getAllToDosByStatus(int status) {
        List<ToDo> todos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TODO + " WHERE " + KEY_STATUS + " = " + status;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ToDo todo = new ToDo();
                todo.setId(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                todo.setNote((cursor.getString(cursor.getColumnIndex(KEY_TODO))));
                todo.setListId(cursor.getInt((cursor.getColumnIndex(KEY_LISTID))));
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return todos;
    }

    public long createList(ListName listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LISTNAME, listName.getList_name());
        long tag_id = db.insert(TABLE_LISTNAMES, null, values);
        return tag_id;
    }

    public List<ListName> getAllLists() {
        List<ListName> listNames = new ArrayList<ListName>();
        String selectQuery = "SELECT  * FROM " + TABLE_LISTNAMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ListName listName = new ListName();
                listName.setId(cursor.getInt((cursor.getColumnIndex(KEY_ID))));
                listName.setList_name(cursor.getString(cursor.getColumnIndex(KEY_LISTNAME)));
                listNames.add(listName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listNames;
    }

    public ListName getListName(long listName_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LISTNAMES + " WHERE " + KEY_ID + " = " + listName_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        ListName listName = new ListName();
        listName.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        listName.setList_name((cursor.getString(cursor.getColumnIndex(KEY_LISTNAME))));
        cursor.close();
        return listName;
    }

    public int updateList(ListName listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LISTNAME, listName.getList_name());
        return db.update(TABLE_LISTNAMES, values, KEY_ID + " = ?", new String[]{String.valueOf(listName.getId())});
    }

    public void deleteList(ListName list) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LISTNAMES, KEY_ID + " = ?", new String[]{String.valueOf(list.getId())});
    }


}

