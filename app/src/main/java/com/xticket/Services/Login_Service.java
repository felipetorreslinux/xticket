package com.xticket.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.xticket.Utils.ProgressLoad;
import com.xticket.Views.View_Principal;

import java.util.Map;

public class Login_Service {

    Activity activity;
    static AlertDialog.Builder builder;
    FirebaseAuth firebaseAuth;
    SharedPreferences.Editor editor;

    public Login_Service(Activity activity){
        this.activity = activity;
        builder = new AlertDialog.Builder(activity);
        firebaseAuth = FirebaseAuth.getInstance();
        editor = activity.getSharedPreferences("profile", Context.MODE_PRIVATE).edit();
    }

    public void access (final String email, final String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        ProgressLoad.close();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        editor.putString("token_access", user.getUid());
                        editor.commit();
                        if(editor.commit()){
                            Intent intent = new Intent(activity, View_Principal.class);
                            activity.startActivity(intent);
                            activity.finishAffinity();
                        }
                    }else{
                        ProgressLoad.close();
                        builder.setTitle("Ops!!!");
                        builder.setMessage("Email e senha inválidos");
                        builder.setPositiveButton("Ok", null);
                        builder.create().show();
                    }
                }
            });
    }
}
