package com.codeclan.example.todo;

/**
 * Created by user on 04/03/2017.
 */

public class ListName {
    private int id;
    private String list_name;

    public ListName() {

    }

    public ListName(String list_name) {
        this.list_name = list_name;
    }

    public ListName(int id, String list_name) {
        this.id = id;
        this.list_name = list_name;
    }

    public int getId() {
        return id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
}
