package com.xticket.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xticket.R;
import com.xticket.Utils.Email;
import com.xticket.Utils.Keyboard;
import com.xticket.Utils.NotificationsApp;
import com.xticket.Utils.ProgressLoad;

public class View_Recovery_Pass extends Activity implements View.OnClickListener {

    ImageView button_back_recovery_pass;
    EditText email_recovery_pass;
    Button button_recovery_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recovery_pass);

        button_back_recovery_pass = (ImageView) findViewById(R.id.button_back_recovery_pass);
        button_back_recovery_pass.setOnClickListener(this);

        email_recovery_pass = (EditText) findViewById(R.id.email_recovery_pass);

        button_recovery_pass = (Button) findViewById(R.id.button_recovery_pass);
        button_recovery_pass.setVisibility(View.GONE);
        button_recovery_pass.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        emailRecovery();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_back_recovery_pass:
                onBackPressed();
                break;

            case R.id.button_recovery_pass:
                recoveryPass();
                break;
        }
    }

    private void emailRecovery(){
        email_recovery_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = charSequence.toString().trim();
                if(Email.check(email) == true){
                    button_recovery_pass.setVisibility(View.VISIBLE);
                }else{
                    button_recovery_pass.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void recoveryPass() {
        Keyboard.close(this, getWindow().getDecorView());
        ProgressLoad.open(this, null, "Verificando Email\nAguarde...", true);
        NotificationsApp.send(this, 0, R.drawable.ic_icon_notification, "xTicket - Recuperação de senha",
                "Sua senha: 123467");
        email_recovery_pass.setText(null);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
