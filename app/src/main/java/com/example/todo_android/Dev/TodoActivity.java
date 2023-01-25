package com.example.todo_android.Dev;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todo_android.Model.CustomAdapter;
import com.example.todo_android.Model.Task;
import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;


public class TodoActivity extends AppCompatActivity {
    ActionBar actionBar;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newTaskName;
    private Button cancelButton, addButton;
    private Todo_l todo_l = Todo_l.getInstance();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adapter = new ArrayAdapter<>(this, R.layout.todo_listview, todo_l.todoName_l);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        actionBar = getSupportActionBar();
        get_appBarColor();
        TextView title = (TextView) findViewById(R.id.todo_name);
        title.setText(todo_l.todo_l.get(todo_l.currentTodo).todoName);
        ListView list_tasks = (ListView) findViewById(R.id.listTask);
        adapter = new CustomAdapter(this, todo_l.todo_l.get(todo_l.currentTodo).task_l);
        list_tasks.setAdapter(adapter);

    }

    @Override
    protected void onStop() { super.onStop(); todo_l.writeJson(true, this); }

    @Override
    protected void onDestroy() { super.onDestroy(); todo_l.writeJson(true, this); }

    @Override
    public void onResume(){super.onResume(); get_appBarColor();}


    private void saveCheckBox() {
        todo_l.writeJson(true, this);
    }

    public void AddButton_Click(View v) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View Popup = getLayoutInflater().inflate(R.layout.popup_createdtask, null);

        newTaskName = (EditText) Popup.findViewById(R.id.new_task_name);

        cancelButton = (Button) Popup.findViewById(R.id.button_cancel);
        addButton = (Button) Popup.findViewById(R.id.button_add);

        dialogBuilder.setView(Popup);
        dialog = dialogBuilder.create();
        dialog.show();

        cancelButton.setOnClickListener(view -> dialog.cancel());

        addButton.setOnClickListener(view -> {
            if (newTaskName != null ) {
                todo_l.todo_l.get(todo_l.currentTodo).task_l.add(new Task(newTaskName.getText().toString()));
                adapter.notifyDataSetChanged();
                todo_l.writeJson(true, this);
            }
            dialog.dismiss();
        });

    }

    public void get_appBarColor() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        String color_string = sh.getString("color", "");
        ColorDrawable color;

        if (color_string == "purple") {
            color = new ColorDrawable(Color.parseColor("#650e97"));
            actionBar.setBackgroundDrawable(color);
        } else if (color_string == "green") {
            color = new ColorDrawable(Color.parseColor("#3aab17"));
            actionBar.setBackgroundDrawable(color);
        } else {
            color = new ColorDrawable(Color.parseColor("#116ab6"));
            actionBar.setBackgroundDrawable(color);
        }
    }

}