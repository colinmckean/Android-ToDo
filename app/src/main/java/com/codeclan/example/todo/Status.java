package com.codeclan.example.todo;

/**
 * Created by user on 06/03/2017.
 */

public enum Status {
    DONE(-1),
    TODO(0),
    INPROGRESS(1);

    private int statusValue;

    private Status(int value){
        this.statusValue = value;
    }

    public int getValue(){
        return statusValue;
    }
    public static Status getStatus(int statusCode){
        switch(statusCode){
            case -1:
                return Status.DONE;
            case 0:
                return Status.TODO;
            case 1:
                return Status.INPROGRESS;
            default:
                return Status.TODO;
        }

    }

}
