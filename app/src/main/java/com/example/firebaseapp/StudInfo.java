package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudInfo extends AppCompatActivity {
    EditText stud_name, stud_prn, stud_class, stud_roll;
    Button addRecord;

    Students student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_info);

        stud_name = (EditText) findViewById(R.id.studentName);
        stud_prn = findViewById(R.id.studentPRN);
        stud_roll = findViewById(R.id.studentRollNo);
        stud_class = (EditText) findViewById(R.id.studentClass);

        addRecord = findViewById(R.id.addBtn);

        student = new Students();

        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roll = Integer.parseInt(stud_roll.getText().toString().trim());
                int prn = Integer.parseInt(stud_prn.getText().toString().trim());
                String name = stud_name.getText().toString().trim();
                String div = stud_class.getText().toString().trim();

                student.setDivision(div);
                student.setName(name);
                student.setRoll_No(roll);
                student.setPRN(prn);

                FirebaseDatabase.getInstance().getReference().child("Students").push().setValue(student);

                Toast.makeText(getApplicationContext(), "Student Added Successfully.", Toast.LENGTH_SHORT).show();

                stud_roll.setText("");
                stud_prn.setText("");
                stud_class.setText("");
                stud_name.setText("");
            }
        });
    }
}