package com.example.todo_android.Dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.todo_android.R;

public class ConnexionPageActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user) {
            Log.d("test", "etonament ça marche");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ForgotPassword_Click(View v) {
        Log.d("test","forgotPassword");
        Intent switchActivityIntent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(switchActivityIntent);;
    }
    public void ValidateButton_Click(View v) {
        Log.d("test","Validate");
        Intent switchActivityIntent = new Intent(this, HomeActivity.class);
        startActivity(switchActivityIntent);
    }
    public void CreateButton_Click(View v) {
        Log.d("test","Create");
        Intent switchActivityIntent = new Intent(this, CreateAccountActivity.class);
        startActivity(switchActivityIntent);
    }
}