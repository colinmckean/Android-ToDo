package com.codeclan.example.todo;

/**
 * Created by user on 06/03/2017.
 */

public enum Status {
    DONE(-1) {
        @Override
        public String toString() {
            return "Done";
        }
    },
    TODO(0) {
        @Override
        public String toString() {
            return "To Do";
        }
    },
    INPROGRESS(1) {
        @Override
        public String toString() {
            return "In Progress";
        }
    };

    private int statusValue;

    Status(int value) {
        this.statusValue = value;
    }

    public int getValue() {
        return statusValue;
    }

    public static Status getStatus(int statusCode) {
        switch (statusCode) {
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
