package com.example.todo_android.Model;

import java.util.ArrayList;
import java.util.List;

public class Task_l {
    public String todoName;
    public ArrayList<Task> task_l = new ArrayList<>();
    public Task_l(String name){
        this.todoName = name;
        this.task_l.add(new Task("test"));
        this.task_l.add(new Task("test2"));
    }
}
