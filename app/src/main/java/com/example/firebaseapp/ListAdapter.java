package com.example.firebaseapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Students> studentsList;

    public ListAdapter(Activity mContext, List<Students> studentsList){
        super(mContext, R.layout.list_item, studentsList);
        this.mContext = mContext;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);
        CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.presentCheckbox);
        TextView name = (TextView) listItemView.findViewById(R.id.nameView);

        Students students = studentsList.get(position);
        name.setText(students.getName());
        checkBox.setText("        " + Integer.toString(students.getRoll_No()));

        return listItemView;
    }
}
