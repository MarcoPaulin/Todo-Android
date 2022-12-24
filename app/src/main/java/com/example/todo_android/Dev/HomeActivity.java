package com.example.todo_android.Dev;

import androidx.appcompat.app.AppCompatActivity;
import  androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.todo_android.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }
}