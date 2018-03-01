package com.gtm.itinerario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class General_Gasto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__gasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String tgasto=getIntent().getStringExtra("tgasto");
        final String concep=getIntent().getStringExtra("concep");
        final String fecha=getIntent().getStringExtra("fecha");
        final String pref_viaticos=getIntent().getStringExtra("pref_viaticos");
        final String m=getIntent().getStringExtra("m");
        final String tipo_pago=getIntent().getStringExtra("tipo_pago");
        final String nTarje=getIntent().getStringExtra("nTarje");

        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String  nPoliza= getIntent().getStringExtra( "nPoliza");
        final String campa = getIntent().getStringExtra( "campa");
        final String   exp = getIntent().getStringExtra( "exp");
        final String  venc  = getIntent().getStringExtra( "venc");
        final String tarCir= getIntent().getStringExtra( "tarCir");
        //Datos Conductor
        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String expedlic = getIntent().getStringExtra("expedlic");
        final String venclic = getIntent().getStringExtra("venclic");
        final String e=getIntent().getStringExtra("e");

        //Datos vehicular
        final String r = getIntent().getStringExtra("r");
        final String es =getIntent().getStringExtra("es");

        final String ejesli = getIntent().getStringExtra ("ejesli");
        final String tipo_vehi= getIntent().getStringExtra("tipo_vehi");
        final String ejesp= getIntent().getStringExtra("ejesp");

        final String a = getIntent().getStringExtra ("a");
        final String f = getIntent().getStringExtra("f");
        final String h = getIntent().getStringExtra("h");
        final String an = getIntent().getStringExtra("an");
        final String l = getIntent().getStringExtra("l");



        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");
        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");
        final String id_supervisor=getIntent().getStringExtra("id_supervisor");
        final String curp_conducto=getIntent().getStringExtra("curp_conducto");
        final String observaciones= getIntent().getStringExtra( "observaciones");


        final String pref_via =getIntent().getStringExtra("pref_via");

        TextView gGasto=(TextView)findViewById(R.id.textView38);
        gGasto.setText( tgasto+"\n" +concep+"\n" +fecha+"\n"+ pref_viaticos+"\n"+ m+"\n"+ tipo_pago+"\n"+ nTarje);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(General_Gasto.this,viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                g.putExtra("tgasto",tgasto)
                        .putExtra("concep",concep)
                        .putExtra("fecha",fecha)
                        .putExtra("pref_viaticos",pref_viaticos)
                        .putExtra("m",m)
                        .putExtra("tipo_pago",tipo_pago)
                        .putExtra("nTarje",nTarje);

                g.putExtra("placas",placas)
                        .putExtra("polizaS",  polizaS)
                        .putExtra("Cfisico",Cfisico)
                        .putExtra("Cconta",Cconta)
                        .putExtra("nPoliza ",nPoliza )
                        .putExtra("campa", campa)
                        .putExtra("exp",exp )
                        .putExtra("venc",venc)
                        .putExtra("tarCir",tarCir)
                        .putExtra("curp_conducto",curp_conducto)
                        .putExtra("observaciones",observaciones);

                //Datos Conductor
                g.putExtra("licenciaO", licenciaO)
                        .putExtra("aptitud",aptitud)
                        .putExtra("bitacora", bitacora)
                        .putExtra("tl", tl)
                        .putExtra("expedlic", expedlic)
                        .putExtra("venclic", venclic)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);
                g.putExtra("pref_via",pref_via);

                //Datos Vehicular
                g.putExtra("ejesli",ejesli)
                        .putExtra( "tipo_vehi",tipo_vehi)
                        .putExtra("ejesp",ejesp)
                        .putExtra("es",es)
                        .putExtra("r",r)
                        .putExtra( "ec",ec )
                        .putExtra("ecr",ecr)
                        .putExtra("ea",ea)
                        .putExtra("ein",ein)
                        .putExtra("ed",ed)
                        .putExtra("er",er)

                        .putExtra( "a",a )

                        .putExtra("f",f)
                        .putExtra("h",h)
                        .putExtra("id_supervisor",id_supervisor)
                        .putExtra("an",an)
                        .putExtra("l",l);
                startActivity(g);
            }
        });
    }

}
