package com.example.todo_android.Dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;

public class HomeActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newTodoName;
    private Button cancelButton, addButton;
    private Todo_l todo_l = new Todo_l();
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        todo_l.AddTodo("test");
        todo_l.AddTodo("test1");
        todo_l.AddTodo("test2");

        adapter = new ArrayAdapter<>(this, R.layout.todo_listview, todo_l.todoName_l);
        ListView listView = (ListView) findViewById(R.id.listViewTodo_l);
        listView.setAdapter(adapter);
    }

    //FirebaseAuth.getInstance().signOut();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void AddButton_Click(View v) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View Popup = getLayoutInflater().inflate(R.layout.popup_createdtodo, null);

        newTodoName = (EditText) Popup.findViewById(R.id.new_todo_name);

        cancelButton = (Button) Popup.findViewById(R.id.button_cancel);
        addButton = (Button) Popup.findViewById(R.id.button_add);

        dialogBuilder.setView(Popup);
        dialog = dialogBuilder.create();
        dialog.show();

        cancelButton.setOnClickListener(view -> dialog.cancel());

        addButton.setOnClickListener(view -> {
            if (newTodoName != null ) {
                todo_l.AddTodo(newTodoName.getText().toString());
                adapter.notifyDataSetChanged();
            }
            dialog.dismiss();
        });

    }

}