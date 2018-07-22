package com.xticket.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xticket.R;
import com.xticket.Utils.Email;

public class View_Login extends Activity implements View.OnClickListener{

    EditText email_login;
    EditText password_login;
    Button button_login;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.view_login);

        email_login = (EditText) findViewById(R.id.email_login);
        password_login = (EditText) findViewById(R.id.password_login);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                String email = email_login.getText().toString().trim();
                String password = password_login.getText().toString().trim();
                openApp(email, password);
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
}
