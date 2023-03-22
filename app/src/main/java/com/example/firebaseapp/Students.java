package com.example.firebaseapp;

import androidx.annotation.NonNull;

public class Students {
    String Name;
    String Division;
    int PRN;
    int Roll_No;

    public Students(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public int getPRN() {
        return PRN;
    }

    public void setPRN(int PRN) {
        this.PRN = PRN;
    }

    public int getRoll_No() {
        return Roll_No;
    }

    public void setRoll_No(int roll_No) {
        Roll_No = roll_No;
    }
}
