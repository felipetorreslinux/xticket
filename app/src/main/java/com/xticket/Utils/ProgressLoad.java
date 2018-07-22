package com.xticket.Utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressLoad {

    static ProgressDialog progressDialog;

    public static void open (Activity activity, String title, String message, boolean cancelable){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void close (){
        progressDialog.dismiss();
    }

}
