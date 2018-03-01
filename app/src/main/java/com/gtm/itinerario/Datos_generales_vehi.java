package com.gtm.itinerario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Datos_generales_vehi extends AppCompatActivity {
TextView datos_vehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales_vehi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datos_vehiculo=(TextView)findViewById(R.id.Dvehi);

        final String Fisico = getIntent().getStringExtra("Fisico");
        final String Conta = getIntent().getStringExtra("Conta");
        final String tarC = getIntent().getStringExtra("tarC");
        final String polizaSeg= getIntent().getStringExtra( "polizaSeg");


        final String tarCir = getIntent().getStringExtra("tarCir");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String  nPoliza= getIntent().getStringExtra( "nPoliza ");
        final String campa = getIntent().getStringExtra( "campa");
        final String   exp = getIntent().getStringExtra( "exp");
        final String  venc  = getIntent().getStringExtra( "venc");
        final String  placas = getIntent().getStringExtra( "placas");

        final String pref_via="3";

        datos_vehiculo.setText("TARJETA DE CIRCULACION= "+tarC+""+tarCir+"\n"
                                +"PLACAS= "+placas+"\n"
                                + "POLIZA= "+polizaSeg+""+polizaS+"\n"
                               +"NUMERO DE POLIZA= "+nPoliza+"\n"
                                +"COMPAÑIA= "+campa+"\n"
                                +"EXPEDICION= "+exp+"\n"
                                +"VENCIIENTO= "+venc+"\n"
                                +"CONTAMINANTES= "+""+ Cconta+Conta+"\n"
                                +"FISICOMECANICO "+Fisico+ Cfisico+"\n" );


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Datos_generales_vehi.this,principalActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        i.putExtra("tarCir",tarCir)
                                .putExtra("placas",placas)
                                .putExtra("polizaS",polizaS)
                                .putExtra("nPoliza",nPoliza)
                                .putExtra("exp",exp)
                                .putExtra("venc",venc)
                                .putExtra("Cconta",Cconta)
                                .putExtra("Cfisico",Cfisico)
                                .putExtra("campa",campa);
                startActivity(i);
            }
        });
        FloatingActionButton g = (FloatingActionButton) findViewById(R.id.g);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Datos_generales_vehi.this,DocActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}