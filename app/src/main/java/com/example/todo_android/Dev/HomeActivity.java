package com.example.todo_android.Dev;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.todo_android.Model.Todo_l;
import com.example.todo_android.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newTodoName;
    private Button cancelButton, addButton;
    private Todo_l todo_l = Todo_l.getInstance();
    private List<String> listName;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
    }

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
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable color;
        switch (item.getItemId()) {
            case R.id.green:

                color = new ColorDrawable(Color.parseColor("#3aab17"));
                actionBar.setBackgroundDrawable(color);
                return true;
            case R.id.blue:

                color = new ColorDrawable(Color.parseColor("#116ab6"));
                actionBar.setBackgroundDrawable(color);
                return true;
            case R.id.purple:

                color = new ColorDrawable(Color.parseColor("#650e97"));
                actionBar.setBackgroundDrawable(color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void movetoTodo() {
        Intent i = new Intent(this , TodoActivity.class);
        startActivity(i);
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