package com.xticket.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xticket.R;

public class View_Login extends Activity {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.view_login);
    }
}
