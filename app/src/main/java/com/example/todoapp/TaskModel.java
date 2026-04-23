package com.example.todoapp;

public class TaskModel {
    private String task;

    public TaskModel(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
