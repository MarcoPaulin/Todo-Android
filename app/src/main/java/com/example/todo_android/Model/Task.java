package com.example.todo_android.Model;

public class Task {
    public String name;
    private String text;
    private Boolean isActive;

    public Task(String name) {
        this.name = name;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return (this.text);
    }
}
