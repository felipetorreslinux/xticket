package com.xticket.FireBase;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class Fire extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
