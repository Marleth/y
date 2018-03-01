package com.gtm.itinerario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Datos_viaticos extends AppCompatActivity {

   TextView Datos_gasto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_viaticos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Datos_gasto=(TextView) findViewById(R.id.gasto);
        final String tgasto= getIntent().getStringExtra("tgasto");
        final String concep= getIntent().getStringExtra("concep");
        final String m= getIntent().getStringExtra("m");
        final String tipo_pago= getIntent().getStringExtra("tipo_pago");
        final String nTarje= getIntent().getStringExtra("nTarje");
        final String fecha= getIntent().getStringExtra("fecha");

        Datos_gasto.setText("TIPO DE GASTO= "+tgasto+"\n"
                            +"CONCEPTO= "+concep+"\n"
                            +"FECHA= "+fecha+"\n"
                            +"MONTO= "+m+"\n"
                            +"TIPO DE PAGO= "+tipo_pago+"\n"
                            +"NUM. DE TARJETA"+nTarje);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
