package com.example.todo_android.Dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todo_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionPageActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText inputEmail, inputPwd;
    TextView error;
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_page);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        inputEmail = findViewById(R.id.email);
        error = findViewById(R.id.error);
        inputPwd = findViewById(R.id.password);
        actionBar = getSupportActionBar();
        get_appBarColor();
    }

    @Override
    public void onResume(){
        super.onResume();
        get_appBarColor();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Êtes-vous sur de vouloir quitter l'application ?");
        dlgAlert.setTitle("Quitter");
        dlgAlert.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ConnexionPageActivity.this.finish();
            }
        });
        dlgAlert.setNegativeButton("Non", null);
        dlgAlert.create().show();
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
        String email = inputEmail.getText().toString();
        String password = inputPwd.getText().toString();

        if ( email != null && password != null) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        connexionSuccesful();
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

    public void connexionSuccesful() {
        Intent switchActivityIntent = new Intent(this, HomeActivity.class);
        startActivity(switchActivityIntent);
    }

    public void CreateButton_Click(View v) {
        Log.d("test","Create");
        Intent switchActivityIntent = new Intent(this, CreateAccountActivity.class);
        startActivity(switchActivityIntent);
    }
}