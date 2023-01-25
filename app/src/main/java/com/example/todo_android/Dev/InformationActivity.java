package com.example.todo_android.Dev;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.todo_android.R;

public class InformationActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        actionBar = getSupportActionBar();
        get_appBarColor();
    }

    @Override
    public void onResume(){super.onResume(); get_appBarColor();}

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