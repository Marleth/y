package com.gtm.itinerario;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

import java.util.ArrayList;

public class Datos_geneerales_doc extends AppCompatActivity {
     TextView datos_conductor, datos_vehiculo;
    Synchronization sincronizar;
    private ProgressDialog progressDialog;
    private ImageButton vehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_geneerales_doc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datos_conductor=(TextView)findViewById(R.id.Dconductor);


        final String id_conductor = getIntent().getStringExtra( "id_conductor");
        //Datos vehiculos
        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String nPoliza= getIntent().getStringExtra( "nPoliza");
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
        final String e=getIntent().getStringExtra("e");
        final String ve=getIntent().getStringExtra("ve");


       // SharedPreferences pref = this.getSharedPreferences("Mipreferencia", Context.MODE_PRIVATE);
        //pref.edit().clear().commit();
       // SharedPreferences.Editor edit = pref.edit();
        //edit.clear();

        final String pref_via="2";
        final String posi="1";

        Button g=(Button)findViewById(R.id.button18);

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Datos_geneerales_doc.this,principalActivity.class);
                i.putExtra("placas",placas)
                        .putExtra("polizaS",  polizaS)
                        .putExtra("Cfisico",Cfisico)
                        .putExtra("Cconta",Cconta)
                        .putExtra("nPoliza ",nPoliza )
                        .putExtra("campa", campa)
                        .putExtra("exp",exp )
                        .putExtra("venc",venc)
                        .putExtra("tarCir",tarCir)
                        .putExtra("id_conductor",id_conductor)
                        .putExtra("posi",posi);

                //Datos Conductor
                i.putExtra("licenciaO", licenciaO)
                        .putExtra("aptitud",aptitud)
                        .putExtra("bitacora", bitacora)
                        .putExtra("tl", tl)
                        .putExtra("ve", ve)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);
                i.putExtra("placas",placas);
                        i.putExtra("pref_via",pref_via);
                startActivity(i);
            }

        });

        datos_conductor.setText("CON LICENCIA= "+licenciaO+"\n"
                                +"APTITUD PSICOFICA= "+aptitud+"\n"
                                +"BITACORA= "+ bitacora+"\n"
                                +"TIPO DE LICENCIA= "+tl+"\n"
                                +"NUMERO DE LICENCIA= "+num_licencia+"\n"
                                +"EXPEDICION= "+exp+"\n"
                                +"VENCIMIENTO"+venc);


        datos_vehiculo=(TextView)findViewById(R.id.textView31);
        datos_vehiculo.setText("TARJETA DE CIRCULACION= "+tarCir+"\n"
                +"PLACAS= "+placas+"\n"
                + "POLIZA= "+polizaS+"\n"
                +"NUMERO DE POLIZA= "+nPoliza+"\n"
                +"COMPAÑIA= "+campa+"\n"
                +"EXPEDICION= "+e+"\n"
                +"VENCIMIENTO= "+ve+"\n"
                +"CONTAMINANTES= "+""+ Cconta+"\n"
                +"FISICOMECANICO "+ Cfisico+"\n" );

    }
    @Override
    public void onBackPressed() {

    }
}
