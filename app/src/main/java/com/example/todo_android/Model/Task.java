package com.example.todo_android.Model;

public class Task {
    public String name;
    private Boolean isFinish;

    public Task(String name) {
        this.name = name;
        this.isFinish = false;
    }

    public void setName(String text){
        this.name = text;
    }

    public boolean getIsFinish() {
        return (isFinish);
    }

    public void setIsFinish(boolean value) {
        isFinish = value;
    }

    public String getName() {
        return (this.name);
    }
}
