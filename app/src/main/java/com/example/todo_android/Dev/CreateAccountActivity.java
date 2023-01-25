package com.example.todo_android.Dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todo_android.Model.Task;
import com.example.todo_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText inputEmail, inputPwd;
    TextView error;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        actionBar = getSupportActionBar();
        get_appBarColor();
        inputEmail = findViewById(R.id.email);
        inputPwd = findViewById(R.id.password);
        error = findViewById(R.id.error);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public void onResume(){
        super.onResume();
        get_appBarColor();

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

    public void ValidateButton_Click(View view) {
        String email = inputEmail.getText().toString();
        String password = inputPwd.getText().toString();
        if (email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        succesCreated();
                    } else {
                        error.setText(task.getException().getMessage());
                        Log.e("error", "lol");
                    }
                }
            });
        } else {
            error.setText("please could you enter an email and password");
        }
    }

    public void succesCreated(){
        Intent switchActivityIntent = new Intent(this, HomeActivity.class);
        startActivity(switchActivityIntent);
    }
}