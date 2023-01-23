package com.example.todo_android.Dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todo_android.Model.CustomAdapter;
import com.example.todo_android.Model.Task;
import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;

import org.json.JSONObject;

public class TodoActivity extends AppCompatActivity {

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
        TextView title = (TextView) findViewById(R.id.todo_name);
        title.setText(todo_l.todo_l.get(todo_l.currentTodo).todoName);
        ListView list_tasks = (ListView) findViewById(R.id.listTask);
        adapter = new CustomAdapter(this, todo_l.todo_l.get(todo_l.currentTodo).task_l);
        list_tasks.setAdapter(adapter);
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

}