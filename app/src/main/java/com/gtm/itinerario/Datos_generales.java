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
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Synchronization;

import java.util.ArrayList;

public class Datos_generales extends AppCompatActivity {

    Synchronization sincronizar;
    private ProgressDialog progressDialog;
    TextView datos_neumaticos,datos_niveles,datos_generales,datos_luces;
    Button cancelar;
   /* @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progressDialog.dismiss();
            try {
                Intent servicio = new Intent(Datos_generales.this, Servicio.class);
                if (servicio != null) {
                    startService(servicio);
                }
            } catch (Exception e) {
            }

            startActivity(new Intent(getBaseContext(), principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP))          ;

            finish();
        }
    };
    static String id_tipo_vehiculo;

    @SuppressLint("HandlerLeak")
    public Handler handlerError = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String message = null;
            try {
                message = (String)msg.obj;
            } catch (Exception ex) {
                message = "Usuario no autorizado";
            } finally {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Datos_generales.this);
                builder.setTitle("Importante");
                builder.setMessage(message);
                builder.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.cancel();

                            }
                        }
                );
                progressDialog.dismiss();
                builder.create();
                builder.show();
            }
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        final String curp_conducto = getIntent().getStringExtra("curp_conducto");

        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");
        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");

        final String id_supervisor=getIntent().getStringExtra("id_supervisor");

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
        final String ve= getIntent().getStringExtra("ve");
        final String id_conductor = getIntent().getStringExtra( "id_conductor");

        final String observaciones= getIntent().getStringExtra( "observaciones");

        final String pref_via="3";



        //Datos niveles
       datos_niveles=(TextView)findViewById(R.id.Dniveles);

        datos_niveles.setText("ACEITE= "+a+"\n"+"LIQUIDO DE FRENOS= "+f+"\n"+"DIRECCION HIDRAULICA= "+h+"\n"+"ANTICONGELANTE= "+an+"\n"+"LIMPIAPARABRISAS= "+l+"\n");


        //Datos generales
        datos_generales=(TextView)findViewById(R.id.neumaticos);

        datos_generales.setText("RUEDA DE REPUESTO= "+r+"\n"+"ESTADO DE LOS NEUMATICOS= "+es+"\n"+"Observaciones= "+observaciones);

        datos_luces=(TextView)findViewById(R.id.Dluces);

        datos_luces.setText("CARRETERA= "+ec+"\n"+"CRUCE= "+ecr+"\n"+"ANTINIEBLA= "+ea+"\n"+"INTERMITENTE= "+ein+"\n"+"DIRECCIONALES= "+ed+"\n"+"REVERSA= "+er);

final String pos3="3";
        Button gg=(Button)findViewById(R.id.button19);
        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Datos_generales.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
                Intent p = new Intent(Datos_generales.this,principalActivity.class);
                p.putExtra("ejesli",ejesli)
                        .putExtra( "tipo_vehi",tipo_vehi)
                        .putExtra("ejesp",ejesp)
                        .putExtra("r",r)
                        .putExtra("es",es)
                        .putExtra( "ec",ec )
                        .putExtra("ecr",ecr)
                        .putExtra("ea",ea)
                        .putExtra("ein",ein)
                        .putExtra("ed",ed)
                        .putExtra("er",er)
                        .putExtra( "a",a )
                        .putExtra( "an",an )
                        .putExtra("f",f)
                        .putExtra("h",h)

                        .putExtra("id_supervisor",id_supervisor)
                        .putExtra("l",l);
                        p.putExtra("pref_via",pref_via)
                                .putExtra("pos3",pos3)
                                .putExtra("id_conductor",id_conductor)
                                .putExtra("ve",ve)
                                .putExtra("curp_conducto",curp_conducto)
                                .putExtra("observaciones",observaciones);

                //Datos vehiculo
                p.putExtra("placas",placas)
                        .putExtra("polizaS",  polizaS)
                        .putExtra("Cfisico",Cfisico)
                        .putExtra("Cconta",Cconta)
                        .putExtra("nPoliza ",nPoliza )
                        .putExtra("campa", campa)
                        .putExtra("exp",exp )
                        .putExtra("venc",venc)
                        .putExtra("tarCir",tarCir);

                //Datos Conductor
                p.putExtra("licenciaO", licenciaO)
                        .putExtra("aptitud",aptitud)
                        .putExtra("bitacora", bitacora)
                        .putExtra("tl", tl)
                        .putExtra("expedlic", expedlic)
                        .putExtra("venclic", venclic)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);
                startActivity(p);

                    /*   final ProgressDialog progressDialog = ProgressDialog.show(Datos_generales.this, "", "Guardando datos...");
                new Thread() {
                    public void run() {
                        String msg = "";
                        try {
                        int connectionStatus = sincronizar.getStatusConnection();
                                if (connectionStatus == Operation.CONNECTION_STATUS_BOOTH_OK || connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK || connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                                    sincronizar.Synchronize();
                                } else {
                                    Log.e("tag", "Error de conexión");

                                }
                            ArrayList<ArrayList<String>> seguro = sincronizar.ExecuteSelect("Select id_tipo_vehiculo from Tipo WHERE tipo= '" + tipo_vehi + "'", null);

                                if (seguro.size() == 1) {
                                    /*** Guarda los registros de la consulta en las variables ****
                                     id_tipo_vehiculo = seguro.get(0).get(0);


                            }
*/

            }

        });
        Button cancelar=(Button)findViewById(R.id.button20);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Datos_generales.this,DocActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                p.putExtra("id_supervisor",id_supervisor);
                startActivity(p);
           }
        });
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = getIntent().getStringExtra("r");
                String es =getIntent().getStringExtra("es");

                String ejesli = getIntent().getStringExtra ("ejesli");
                final String tipo_vehi= getIntent().getStringExtra("tipo_vehi");
                String ejesp= getIntent().getStringExtra("ejesp");

                String a = getIntent().getStringExtra ("a");
                String f = getIntent().getStringExtra("f");
                String h = getIntent().getStringExtra("h");
                String an = getIntent().getStringExtra("an");
                String l = getIntent().getStringExtra("l");



                String ec=getIntent().getStringExtra("ec");
                String ecr=getIntent().getStringExtra("ecr");
                String ea=getIntent().getStringExtra("ea");
                String ein=getIntent().getStringExtra("ein");
                String ed=getIntent().getStringExtra("ed");
                String er=getIntent().getStringExtra("er");
             /*   final ProgressDialog progressDialog = ProgressDialog.show(Datos_generales.this, "", "Guardando datos...");
                new Thread() {
                    public void run() {
                        String msg = "";
                        try {
                        int connectionStatus = sincronizar.getStatusConnection();
                                if (connectionStatus == Operation.CONNECTION_STATUS_BOOTH_OK || connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK || connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                                    sincronizar.Synchronize();
                                } else {
                                    Log.e("tag", "Error de conexión");

                                }
                            ArrayList<ArrayList<String>> seguro = sincronizar.ExecuteSelect("Select id_tipo_vehiculo from Tipo WHERE tipo= '" + tipo_vehi + "'", null);

                                if (seguro.size() == 1) {
                                    /*** Guarda los registros de la consulta en las variables ****
                                     id_tipo_vehiculo = seguro.get(0).get(0);


                            }


                            Intent i = new Intent(Datos_generales.this,principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("ejesli",ejesli);
                i.putExtra( "tipo_vehi",tipo_vehi);
                i.putExtra("ejesp",ejesp);

                i .putExtra("es",es);
                i .putExtra("r",r);


                i.putExtra( "ec",ec );
                i.putExtra("ecr",ecr);
                i.putExtra("ea",ea);
                i.putExtra("ein",ein);
                i.putExtra("ed",ed);
                i.putExtra("er",er);

                i.putExtra( "a",a );
                i.putExtra("f",f);
                i.putExtra("h",h);
                i.putExtra("an",an);
                i.putExtra("l",l);
                        startActivity(i);

                      /*  } catch (Exception e) {
                                Log.e("tag", e.getMessage());
                                Message message = handlerError.obtainMessage();
                                message.obj = "Error al obtener datos del usuario.";
                                handlerError.sendMessage(message);
                            }
                        }
                    }.start();

                }
        }
        );

        FloatingActionButton guardar = (FloatingActionButton) findViewById(R.id.g);
       guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Datos_generales.this,vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);

            }
        });
        */
    }
    @Override
    public void onBackPressed() {

    }
}
