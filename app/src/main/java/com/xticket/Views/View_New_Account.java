package com.xticket.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.xticket.R;

public class View_New_Account extends Activity implements View.OnClickListener {

    ImageView button_back_new_account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_new_account);

        button_back_new_account = (ImageView) findViewById(R.id.button_back_new_account);
        button_back_new_account.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_back_new_account:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
