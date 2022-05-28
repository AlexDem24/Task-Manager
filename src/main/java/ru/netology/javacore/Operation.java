package ru.netology.javacore;

public class Operation {
    private String type;
    private String task;

    public Operation(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
