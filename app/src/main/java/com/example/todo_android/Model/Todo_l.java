package com.example.todo_android.Model;

import java.util.ArrayList;
import java.util.List;

public class Todo_l {
    private List<Task_l> todo_l;
    public List<String> todoName_l;



    private Todo_l() {
        todo_l = new ArrayList<Task_l>();
        todoName_l = new ArrayList<String>();
    }

    private static Todo_l INSTANCE = new Todo_l();

    public static Todo_l getInstance() {
        if (INSTANCE == null) {
            synchronized (Todo_l.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Todo_l();
                }
            }
        }
        return INSTANCE;
    }

    public void  AddTodo(String todoName) {
        todo_l.add(new Task_l(todoName));
        todoName_l.add(todoName );
    }

    public List<String> GetListName() {
        List<String> todoName_L = new ArrayList<String>();

        for (Task_l todo : todo_l) {
            todoName_L.add(todo.todoName);
        }
        return (todoName_L);
    }
}