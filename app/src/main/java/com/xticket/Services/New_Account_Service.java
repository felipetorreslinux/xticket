package com.xticket.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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
    static FirebaseAuth firebaseAuth;
    static FirebaseUser firebaseUser;
    static AlertDialog.Builder builder;


    public New_Account_Service(Activity activity){
        this.activity = activity;
        builder = new AlertDialog.Builder(activity);
        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void register (final String token, final String name, final String email, final String username, final String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> insert = new HashMap<>();
                    insert.put("token", token);
                    insert.put("name", name);
                    insert.put("email", email);
                    insert.put("thumb", "");
                    insert.put("type", "admin");
                    insert.put("username", username);

                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(name)
                            .build();
                    firebaseUser.updateProfile(profileChangeRequest);

                    databaseReference.child(firebaseUser.getUid()).setValue(insert, new DatabaseReference.CompletionListener() {
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
                }else{
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthUserCollisionException e) {
                        ProgressLoad.close();
                        builder.setTitle("Ops!!!");
                        builder.setMessage("Email já cadastrado");
                        builder.setPositiveButton("Ok", null);
                        builder.create().show();
                    } catch(Exception e) {}
                }
            }
        });

    }

}
