package com.gtm.itinerario;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Reporte_Facturacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte__facturacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String tipogasto=getIntent().getStringExtra("tipogasto");
        final String rfc=getIntent().getStringExtra("rfc");
        final String rfcEmp=getIntent().getStringExtra("rfcEmp" );
        final String rqr=getIntent().getStringExtra("rqr" );
        final String s=getIntent().getStringExtra("s" );

        TextView Factu=(TextView)findViewById(R.id.Factu);
        Factu.setText(tipogasto+"\n"+rfc+"\n"+ rfcEmp+"\n"+rqr+"\n"+s);

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
