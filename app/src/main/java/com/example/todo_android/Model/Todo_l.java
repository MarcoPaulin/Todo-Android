package com.example.todo_android.Model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Todo_l {
    public List<Task_l> todo_l;
    public List<String> todoName_l;
    public int currentTodo;



    private Todo_l() {
        todo_l = new ArrayList<Task_l>();
        todoName_l = new ArrayList<String>();
        currentTodo = 0;


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

    public String writeJson(boolean write, Context context) {
        String Filepath = context.getFilesDir().getAbsolutePath() + "/" + "TodoData.json";
        Gson gson = new GsonBuilder().serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        Type listType = new TypeToken<Todo_l>(){}.getType();
        String jsonResult = gson.toJson(this, listType);
        if (write) {
            try {

                File file = new File(Filepath);
                file.delete();
                create(context, "TodoData.json", jsonResult);
            } catch (Exception e) {
                System.out.println("exception occurred" + e);
            }
        }
        return  (jsonResult);

    }

    public void setTodo(Todo_l recover) {
        this.todo_l = recover.todo_l;
        this.currentTodo = recover.currentTodo;
        this.todoName_l = recover.todoName_l;
    }

    public void recoverTodo(String json){
        try {
            Gson gson = new GsonBuilder().serializeNulls()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();
            JSONObject jsonObject = new JSONObject(json);
            Type listType = new TypeToken<Todo_l>(){}.getType();
            Todo_l recover =  gson.fromJson(jsonObject.toString(), listType);
            setTodo(recover);
            Log.d("test", recover.toString());
        } catch (Exception e) {
            System.out.println("exception occurred" + e);
        }
    }

    private boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, context.MODE_APPEND);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }
    }

}