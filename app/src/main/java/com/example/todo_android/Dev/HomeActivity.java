package com.example.todo_android.Dev;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newTodoName;
    private Button cancelButton, addButton;
    private Todo_l todo_l = Todo_l.getInstance();
    private ArrayAdapter adapter;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        actionBar = getSupportActionBar();
        get_appBarColor();

        boolean isFilePresent = isFilePresent(this, "TodoData.json");
        if(isFilePresent) {
            String jsonString = read(this, "TodoData.json");
            todo_l.recoverTodo(jsonString);
        } else {
            try {
                create(this, "TodoData.json", todo_l.writeJson(false, this));
             } catch (Exception e) {
            // Display message when exception occurs
                System.out.println("exception occurred" + e);
            }

        }
        adapter = new ArrayAdapter<>(this, R.layout.todo_listview, todo_l.todoName_l);
        ListView listView = (ListView) findViewById(R.id.listViewTodo_l);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                todo_l.currentTodo = position;
                movetoTodo();
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() { super.onStop(); todo_l.writeJson(true, this); }

    @Override
    protected void onDestroy() { super.onDestroy(); todo_l.writeJson(true, this); }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Êtes-vous sur de vouloir vous déconnecter ?");
        dlgAlert.setTitle("Déconnexion");
        dlgAlert.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HomeActivity.this.finish();
            }
        });
        dlgAlert.setNegativeButton("Non", null);
        dlgAlert.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();

        ColorDrawable color;
        switch (item.getItemId()) {
            case R.id.green:
                color = new ColorDrawable(Color.parseColor("#3aab17"));
                myEdit.putString("color", "green");
                myEdit.commit();
                actionBar.setBackgroundDrawable(color);
                return true;
            case R.id.blue:

                color = new ColorDrawable(Color.parseColor("#116ab6"));
                actionBar.setBackgroundDrawable(color);
                myEdit.putString("color", "blue");
                myEdit.commit();
                return true;
            case R.id.purple:

                color = new ColorDrawable(Color.parseColor("#650e97"));
                actionBar.setBackgroundDrawable(color);
                myEdit.putString("color", "purple");
                myEdit.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    private void movetoTodo() {
        Intent i = new Intent(this , TodoActivity.class);
        startActivity(i);
    }


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
                todo_l.writeJson(true, this );
            }
            dialog.dismiss();
        });


    }

    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private void create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, context.MODE_APPEND);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("exception occurred" + fileNotFound);
        } catch (IOException ioException) {
            System.out.println("exception occurred" + ioException);
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

}