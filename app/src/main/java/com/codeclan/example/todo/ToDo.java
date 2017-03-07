package com.codeclan.example.todo;

/**
 * Created by user on 03/03/2017.
 */

public class ToDo {

    private int id;
    private int status;
    private int listId;
    private String note;

    public ToDo() {
    }

    public ToDo(String note, int status) {
        this.note = note;
        this.status = status;
    }

    public ToDo(int id, String note, int status) {
        this.id = id;
        this.note = note;
        this.status = status;
    }

    public ToDo(int status, int listId, String note) {
        this.status = status;
        this.listId = listId;
        this.note = note;
    }

    public long getId() {
        return this.id;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }

    public int getListId() {
        return listId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

}
