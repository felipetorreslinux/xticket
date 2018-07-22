package com.xticket.FireBase;

import android.app.Activity;

import com.google.firebase.database.DatabaseReference;

public class DataBase {

    Activity activity;
    DatabaseReference databaseReference;

    public DataBase(Activity activity){
        this.activity = activity;
    }

}
