package com.xticket.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xticket.R;
import com.xticket.Utils.Email;

public class View_Login extends Activity implements View.OnClickListener{

    LinearLayout box_login;
    EditText email_login;
    EditText password_login;
    Button button_login;
    TextView button_recovery_pass;
    TextView button_new_account;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.view_login);

        box_login = (LinearLayout) findViewById(R.id.box_login);
        email_login = (EditText) findViewById(R.id.email_login);
        password_login = (EditText) findViewById(R.id.password_login);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
        button_recovery_pass = (TextView) findViewById(R.id.button_recovery_pass);
        button_recovery_pass.setOnClickListener(this);
        button_new_account = (TextView) findViewById(R.id.button_new_account);
        button_new_account.setOnClickListener(this);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        animation(box_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                String email = email_login.getText().toString().trim();
                String password = password_login.getText().toString().trim();
                openApp(email, password);
                break;

            case R.id.button_recovery_pass:
                Intent recovery_pass = new Intent(View_Login.this, View_Recovery_Pass.class);
                startActivity(recovery_pass);
                break;

            case R.id.button_new_account:
                Intent new_account = new Intent(View_Login.this, View_New_Account.class);
                startActivity(new_account);
                break;
        }
    }

    private void openApp(String email, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ops!!!");
        if(email.isEmpty()){
            builder.setMessage("Informe seu email");
            builder.setPositiveButton("Ok", null);
            builder.create().show();
        }else if(Email.check(email) == false){
            builder.setMessage("Email é inválido");
            builder.setPositiveButton("Ok", null);
            builder.create().show();
        }else if(password.isEmpty()){
            builder.setMessage("Informe sua senha");
            builder.setPositiveButton("Ok", null);
            builder.create().show();
        }else{

        }
    }

    private void animation (LinearLayout linearLayout){
        Animation animation = new TranslateAnimation(0,0,2000,0);
        animation.setFillEnabled(true);
        animation.setDuration(500);
        animation.start();
        linearLayout.setAnimation(animation);
    }
}
