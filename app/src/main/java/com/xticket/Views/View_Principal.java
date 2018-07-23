package com.xticket.Views;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.xticket.R;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class View_Principal extends Activity implements View.OnClickListener {

    static FirebaseUser firebaseUser;

    ImageView image_profile_view_principal;
    TextView name_profile_view_principal;
    TextView recado_profile_name_view_principal;

    FrameLayout container_central;

    LinearLayout bottom_eventos_principal;
    LinearLayout bottom_buscar_principal;
    LinearLayout bottom_alertas_principal;
    LinearLayout bottom_perfil_principal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);

        image_profile_view_principal = (ImageView) findViewById(R.id.image_profile_view_principal);
        image_profile_view_principal.setOnClickListener(this);
        name_profile_view_principal = (TextView) findViewById(R.id.name_profile_view_principal);
        recado_profile_name_view_principal = (TextView) findViewById(R.id.recado_profile_name_view_principal);

        container_central = (FrameLayout) findViewById(R.id.container_central);

        bottom_eventos_principal = (LinearLayout) findViewById(R.id.bottom_eventos_principal);
        bottom_eventos_principal.setOnClickListener(this);

        bottom_buscar_principal = (LinearLayout) findViewById(R.id.bottom_buscar_principal);
        bottom_buscar_principal.setOnClickListener(this);

        bottom_alertas_principal = (LinearLayout) findViewById(R.id.bottom_alertas_principal);
        bottom_alertas_principal.setOnClickListener(this);

        bottom_perfil_principal = (LinearLayout) findViewById(R.id.bottom_perfil_principal);
        bottom_perfil_principal.setOnClickListener(this);

        bottom_eventos_principal.setAlpha(1.0f);
        bottom_buscar_principal.setAlpha(0.5f);
        bottom_alertas_principal.setAlpha(0.5f);
        bottom_perfil_principal.setAlpha(0.5f);


    }

    @Override
    public void onResume() {
        super.onResume();
        infoProfile();
    }

    private void infoProfile() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Picasso.with(this)
                .load(R.drawable.no_thmb)
                .transform(new CropCircleTransformation())
                .into(image_profile_view_principal);
        name_profile_view_principal.setText("Olá, "+firebaseUser.getDisplayName()+".");
        recado_profile_name_view_principal.setText("sejas muito bem vindo");
        recado_profile_name_view_principal.setAlpha(0.7f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_profile_view_principal:

                break;

            case R.id.bottom_eventos_principal:
                bottom_eventos_principal.setAlpha(1.0f);
                bottom_buscar_principal.setAlpha(0.5f);
                bottom_alertas_principal.setAlpha(0.5f);
                bottom_perfil_principal.setAlpha(0.5f);
                break;

            case R.id.bottom_buscar_principal:
                bottom_eventos_principal.setAlpha(0.5f);
                bottom_buscar_principal.setAlpha(1.0f);
                bottom_alertas_principal.setAlpha(0.5f);
                bottom_perfil_principal.setAlpha(0.5f);
                break;

            case R.id.bottom_alertas_principal:
                bottom_eventos_principal.setAlpha(0.5f);
                bottom_buscar_principal.setAlpha(0.5f);
                bottom_alertas_principal.setAlpha(1.0f);
                bottom_perfil_principal.setAlpha(0.5f);
                break;

            case R.id.bottom_perfil_principal:
                bottom_eventos_principal.setAlpha(0.5f);
                bottom_buscar_principal.setAlpha(0.5f);
                bottom_alertas_principal.setAlpha(0.5f);
                bottom_perfil_principal.setAlpha(1.0f);
                break;
        }
    }
}
