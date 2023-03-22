package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    Button logout, studInfo, studAttendance;
    TextView userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button) findViewById(R.id.logoutBtn);
        userDetails = (TextView) findViewById(R.id.userDetails);
        studInfo = (Button) findViewById(R.id.studInfo);
        studAttendance = (Button) findViewById(R.id.enterAttendance);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user == null){
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
        else{
            userDetails.setText("Email: " + user.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        studInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudInfo.class);
                startActivity(i);
            }
        });

        studAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AttendanceActivity.class);
                startActivity(i);
            }
        });
    }
}