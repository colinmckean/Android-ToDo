package com.codeclan.example.todo;

/**
 * Created by user on 04/03/2017.
 */

public class ListName {
    private int id;
    private String list_name;

    public ListName() {

    }

    public ListName(int id, String list_name) {
        this.id = id;
        this.list_name = list_name;
    }

    //Unleash the getters!
    public int getId() {
        return id;
    }

    public String getList_name() {
        return list_name;
    }

}
