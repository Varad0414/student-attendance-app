package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    ListView listView;
    List<Students> studentsList;

    DatabaseReference databaseReference;
    Button reportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        listView = findViewById(R.id.listView);
        reportBtn = findViewById(R.id.reportBtn);
        studentsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentsList.clear();

                for (DataSnapshot studentDatasnap : snapshot.getChildren()){
                    Students students = studentDatasnap.getValue(Students.class);
                    studentsList.add(students);
                }

                ListAdapter adapter = new ListAdapter(AttendanceActivity.this, studentsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AttendanceReport.class);
                startActivity(i);

            }
        });
    }
}