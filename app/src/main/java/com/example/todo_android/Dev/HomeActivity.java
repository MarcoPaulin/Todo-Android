package com.example.todo_android.Dev;

import androidx.appcompat.app.AppCompatActivity;
import  androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        Todo_l todo_l = new Todo_l();
        todo_l.AddTodo("test");
        todo_l.AddTodo("test1");
        todo_l.AddTodo("test2");

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.todo_listview, todo_l.GetListName());
        ListView listView = (ListView) findViewById(R.id.listViewTodo_l);
        listView.setAdapter(adapter);
    }
}