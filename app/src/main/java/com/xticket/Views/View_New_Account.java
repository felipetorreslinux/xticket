package com.xticket.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.xticket.R;
import com.xticket.Services.New_Account_Service;
import com.xticket.Splash;
import com.xticket.Utils.Email;
import com.xticket.Utils.Keyboard;
import com.xticket.Utils.ProgressLoad;

public class View_New_Account extends Activity implements View.OnClickListener {

    private String TOKEN_FIREBASE = null;
    New_Account_Service service;

    ImageView button_back_new_account;
    EditText name_new_account;
    EditText email_new_account;
    EditText username_new_account;
    EditText password_new_account;

    Button button_new_account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_new_account);

        service = new New_Account_Service(this);

        TOKEN_FIREBASE = FirebaseInstanceId.getInstance().getToken();

        button_back_new_account = (ImageView) findViewById(R.id.button_back_new_account);
        button_back_new_account.setOnClickListener(this);

        name_new_account = (EditText) findViewById(R.id.name_new_account);
        email_new_account = (EditText) findViewById(R.id.email_new_account);
        username_new_account = (EditText) findViewById(R.id.username_new_account);
        password_new_account = (EditText) findViewById(R.id.password_new_account);

        button_new_account = (Button) findViewById(R.id.button_new_account);
        button_new_account.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_back_new_account:
                onBackPressed();
                break;

            case R.id.button_new_account:
                regiterNewAccount();
                break;

        }
    }

    private void regiterNewAccount() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Ops!!!");
        String name = name_new_account.getText().toString().trim();
        String email = email_new_account.getText().toString().trim();
        String username = username_new_account.getText().toString().trim();
        String password = password_new_account.getText().toString().trim();

        if(name.isEmpty()) {
            builder.setMessage("Nome não pode ficar vazio");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else if(email.isEmpty()){
            builder.setMessage("Email não pode ficar vazio");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else if(Email.check(email) == false){
            builder.setMessage("Email informado não é válido");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else if (username.isEmpty()){
            builder.setMessage("Usuário não pode ficar vazio");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else if( username.length() < 6){
            builder.setMessage("Nome de usuário muito curto.\nMínimo é de 6 caracteres");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else if(password.isEmpty()){
            builder.setMessage("Senha não pode ficar vazia");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }else{
            Keyboard.close(this, getWindow().getDecorView());
            ProgressLoad.open(this, null, "Cadastrando usuário", false);
            service.register(TOKEN_FIREBASE, name, email, username, password);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
