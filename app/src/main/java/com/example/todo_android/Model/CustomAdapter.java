package com.example.todo_android.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todo_android.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Task> {

    public CustomAdapter(Context context, ArrayList<Task> task) {
        super(context, 0, task);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task ts = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_listview, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textView);
        CheckBox sw = convertView.findViewById(R.id.switch1);

        textView.setText(ts.getName());
        sw.setChecked(ts.getIsFinish());
        sw.setOnClickListener(view -> {
            ts.setIsFinish(sw.isChecked());
        });
        return convertView;
    }
}
