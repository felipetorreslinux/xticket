package com.xticket;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.xticket.Views.View_Login;
import com.xticket.Views.View_Principal;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_splash);
       loginApp();
    }

    private void loginApp(){
        SharedPreferences sharedPreferences = getSharedPreferences("profile", MODE_PRIVATE);
        if(sharedPreferences != null){
            if(sharedPreferences.getString("token_access", "").equals("")){
                openLogin();
            }else{
                openPrincipal();
            }
        }else{
            openLogin();
        }
    }

    private void openLogin(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, View_Login.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void openPrincipal(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, View_Principal.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
