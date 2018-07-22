package com.xticket.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xticket.R;

public class View_Principal extends Activity implements View.OnClickListener {

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println(firebaseUser.getEmail());

    }

    @Override
    public void onClick(View view) {

    }
}
