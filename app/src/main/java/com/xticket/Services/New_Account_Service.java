package com.xticket.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xticket.R;
import com.xticket.Utils.ProgressLoad;

import java.util.HashMap;
import java.util.Map;

public class New_Account_Service {

    Activity activity;
    static DatabaseReference databaseReference;
    static AlertDialog.Builder builder;

    public New_Account_Service(Activity activity){
        this.activity = activity;
        builder = new AlertDialog.Builder(activity);
        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot().child("users");

    }


    public void register (final String token, final String name, final String email, final String username, final String password){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("email").getValue().equals(email)){
                        ProgressLoad.close();
                        builder.setTitle("Ops!!!");
                        builder.setMessage("Email já está cadastrado");
                        builder.setPositiveButton("Ok", null);
                        builder.create().show();
                        return;
                    }else{
                        Map<String, Object> insert = new HashMap<>();
                        insert.put("token", token);
                        insert.put("name", name);
                        insert.put("email", email);
                        insert.put("type", "admin");
                        insert.put("username", username);
                        insert.put("password", password);
                        databaseReference.push().setValue(insert, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if(databaseError != null){
                                    ProgressLoad.close();
                                    builder.setTitle("Ops!!!");
                                    builder.setMessage("Erro ao cadastrar.");
                                    builder.setPositiveButton("Ok", null);
                                    builder.create().show();
                                }else{
                                    ProgressLoad.close();
                                    builder.setTitle(R.string.app_name);
                                    builder.setMessage("Usuário cadastrado com sucesso.");
                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            builder.create().dismiss();
                                            activity.finish();
                                        }
                                    });
                                    builder.create().show();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
