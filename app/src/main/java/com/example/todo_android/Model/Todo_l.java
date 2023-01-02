package com.example.todo_android.Model;

import java.util.ArrayList;
import java.util.List;

public class Todo_l {
    List<String> todo_l;
    public Todo_l() {
        todo_l = new ArrayList<String>();
    }

    public void  AddTodo(String todoName) {
        todo_l.add(todoName);
    }

    public List<String> GetListName() {
        return (todo_l);
    }
}
