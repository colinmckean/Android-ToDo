package com.codeclan.example.todo;

/**
 * Created by user on 03/03/2017.
 */

public class ToDo {

    private int id;
    private int status;
//    private int listId;
//    private String title;
//    private String priorityId;
//    private String description;
    private String note;

    //Add Constructors.
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

    // here be getters
    public long getId() {
        return this.id;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }

    // get set for the setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
