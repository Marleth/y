package com.gtm.itinerario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class NivelesActivity extends AppCompatActivity {
   private Switch aceite,liq,dire,anti,limp;
    private ImageView nivel;
    private Button guardar,cancelar;
TextView niveles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        niveles=(TextView)findViewById(R.id.txtniveles);
        aceite=(Switch)findViewById(R.id.txaceites);
        liq=(Switch)findViewById(R.id.txtfrenos);
        dire=(Switch)findViewById(R.id.txtdireccion);
        anti=(Switch)findViewById(R.id.txtanti);
        limp=(Switch)findViewById(R.id.txtlimpi);
        nivel=(ImageView)findViewById(R.id.imagnivel);
        guardar=(Button)findViewById(R.id.btguardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(NivelesActivity.this, vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(g);
                finish();

            }
        });
        cancelar=(Button)findViewById(R.id.button9);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(NivelesActivity.this);
                builder.setMessage("Deseas salir")
                        .setTitle("Advertencia")
                        .setIcon(getResources().getDrawable(R.drawable.ic_advertencia))
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                        .setPositiveButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent g = new Intent(NivelesActivity.this, vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(g);
                                        finish();
                                    }
                                });
                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
    @Override
    public void onBackPressed() {

    }


}
