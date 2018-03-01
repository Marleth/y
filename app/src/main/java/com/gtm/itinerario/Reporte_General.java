package com.gtm.itinerario;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Reporte_General extends AppCompatActivity {
    static String id_licencia="",  id_tipo_seguro="",  id_seguro="" ;
    private ProgressDialog progressDialog;

/*
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String message = null;
            try {
                message = (String)msg.obj;
            } catch (Exception ex) {
                message = "-3";
            } finally {
                android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(Reporte_General.this);
                if (message.contains("-4")) {
                    alertDialog.setTitle("Advertencia:");
                    alertDialog.setMessage("Error");
                    alertDialog.setNegativeButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.cancel();
                                }
                            }
                    );
                } else if (message.equals("-3")) {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(message);
                    alertDialog.setNegativeButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.cancel();
                                }
                            }
                    );
                } else if (message.contains(",-2")) {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(message);
                    alertDialog.setNegativeButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.cancel();
                                }
                            }
                    );
                } else if (message.equals("-1")) {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(message);
                    alertDialog.setNegativeButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.cancel();
                                }
                            }
                    );
                } else if (message.equals("0")) {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(message);
                    alertDialog.setNegativeButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.cancel();
                                }
                            }
                    );
                } else /*if (message.equals("1"))  {
                    alertDialog.setMessage("Registro guardado correctamente");
                    alertDialog.setTitle("Atención!!");
                    alertDialog.setCancelable(false);
                    alertDialog.setNeutralButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                    startActivity(new Intent(getBaseContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                                }
                            }
                    );
                }
                alertDialog.create().show();
            }
        }
    };
   */

    TelephonyManager mngr;
    String imei="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte__general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TelephonyManager mngr = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);

        final String id_conductor = getIntent().getStringExtra( "id_conductor");

        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String tarCir= getIntent().getStringExtra( "tarCir");
        //Datos Conductor

        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String ve = getIntent().getStringExtra("ve");
        final String e=getIntent().getStringExtra("e");

        final String observaciones= getIntent().getStringExtra( "observaciones");
        //Datos vehicular
        final String r = getIntent().getStringExtra("r");
        final String es =getIntent().getStringExtra("es");
        final String   id_vehiculo  = getIntent().getStringExtra ("id_vehiculo");
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
        final String  id_supervisor= getIntent().getStringExtra( "id_supervisor");

  final String estado_niveles=getIntent().getStringExtra("estado_niveles");
        final String estado_luces=getIntent().getStringExtra("estado_luces");


        mngr = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
        imei = mngr.getDeviceId();

                TextView dv=(TextView)findViewById(R.id.textView43);
                dv.setText(    "********  DATOS GENERALES  ********"+"\n"+"\n"+
                                "CURP SUPERVISOR: "+id_supervisor+"\n"+
                                "ID PLACAS: "+id_vehiculo+"\n"+
                                "CURP CONDUCTOR: "+id_conductor+"\n"
                                +"TARJETA DE CIRCULACION: "+tarCir+"\n"
                                + "POLIZA: "+polizaS+"\n"
                                +"CONTAMINANTES: "+""+ Cconta+"\n"
                                +"FISICOMECANICO: "+ Cfisico+"\n"
                                +"REPUESTO DE NEUMATICO: "+r + "\n"
                                +"ESTADO DE LOS NEUMATICOS: "+es+"\n"
                                +"IMEI: "+imei+"\n"
                                +"OBSERVACIONES: "+observaciones+"\n"+"\n"+

                                "********  LUCES  ********"+"\n"+"\n"+
                                "CARRETERA: "+ec+"\n"
                                +"CRUCE: "+ecr+"\n"
                                +"ANTINIEBLA: "+ea+"\n"
                                +"INTERMITENTE: "+ein+"\n"
                                +"DIRECCIONALES: "+ed
                                +"\n"+"REVERSA: "+er+

                                "******** NIVELES  ********"+"\n"+"\n"+
                                "ACEITE: "+a+"\n"
                                +"FRENOS: "+f+"\n"
                                +"HIDRAULICA: "+h+"\n"
                                +"ANTICONGELANTE: "+an+"\n"
                                +"LIMPIAPARABRISAS= "+l+"\n"
                                );


//Guardar
        FloatingActionButton guardar = (FloatingActionButton) findViewById(R.id.g);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Synchronization sincronizar;
                sincronizar = new Synchronization(Reporte_General.this);
                final String pref_via = null;

              //- final ProgressDialog progressDialog = ProgressDialog.show(Reporte_General.this, "", "Guardando datos...");

                new Thread() {
                    public void run() {
                        String msg = "";

                        try {

                            //Insertar en la tabla Seguro
                            String query = "INSERT INTO Vehiculo_General(" +
                                    " id_conductor,id_supervisor,id_vehiculo,tar_circulacion," +
                                    "poliza,contaminantes,fisico,ecarretera,ecruce,eantiniebla," +
                                    "eintermitentes,edireccionales," +
                                    "ereserva,eaceite,efrenos,ehidraulica,eanticongelante," +
                                    "elimpiaparabrisas,repuesto_neu,estado_neu,observaciones,status_vehi,imei) " +
                                    "values ("+id_conductor+","+id_supervisor+","+ id_vehiculo+","+tarCir+","+polizaS+","+Cconta+","+ Cfisico + "," + ec + "," + ecr + ","+ea+","+ein+","+ed+","+er+","+a+","+f+","+h+","+an+","+l+","+r+","+es+",'"+observaciones + "',0,"+imei+");";

                            sincronizar.saveInQueue(query);

                            int statusConnection = sincronizar.getStatusConnection();
                            if((statusConnection==Operation.CONNECTION_STATUS_WIFI_OK )|| (statusConnection==Operation.CONNECTION_STATUS_MOBILE_OK)){
                                sincronizar.ApplyChanges();
                            }

                        } catch (Exception e) {
                            Log.e("tag", e.getMessage());

                        }
                    }
                }.start();
            }

        });




        //Modificar
        FloatingActionButton modificar = (FloatingActionButton) findViewById(R.id.m);
       modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] repuesto={"","Documentacion","Vehiculo","Carga","Viaticos"};

                final ArrayAdapter<String> adp = new ArrayAdapter<String>(Reporte_General.this,
                        android.R.layout.simple_spinner_item,repuesto);

                final Spinner repu= new Spinner(Reporte_General.this);
                repu.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                repu.setAdapter(adp);

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Reporte_General.this);
                builder.setMessage("Modificar");


                repu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                        if(position==1){
                            String pref_via=null;
                            Intent i=new Intent(Reporte_General.this,principalActivity.class) ;
                            i.putExtra("pref_via",pref_via)
                            .putExtra("id_supervisor",id_supervisor);
                            startActivity(i);
                        }
                        if(position==2){
                            final String pref_via="2";
                            Intent i=new Intent(Reporte_General.this,principalActivity.class);
                            i.putExtra("id_supervisor",id_supervisor);
                            i.putExtra("pref_via",pref_via);
                            startActivity(i);
                        }
                        if(position==3){
                            final String pref_via="3";
                            Intent i=new Intent(Reporte_General.this,principalActivity.class);
                            i.putExtra("pref_via",pref_via);
                            i.putExtra("id_supervisor",id_supervisor);
                            startActivity(i);
                        }
                        if(position==4){
                            final String pref_via="4";
                            Intent i=new Intent(Reporte_General.this,principalActivity.class);
                            i.putExtra("pref_via",pref_via);
                            i.putExtra("id_supervisor",id_supervisor);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }  );


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.setView(repu);
                builder.create().show();
            }
        });
    }

}
    /*Toast.makeText(getApplicationContext(), "Guardado con exito",
                        Toast.LENGTH_LONG).show();
                final String pref_via=null;
                Intent i=new Intent(Reporte_General.this,principalActivity.class);
                i.putExtra("pref_via",pref_via);
                startActivity(i);


                 String estado_niveles="";

                    if(a.equals("1") && f.equals("1") && h.equals("1") && an.equals("1") && l.equals("1")) {
                        estado_niveles="Bueno";
                    }else

                    if(a.equals("1") && f.equals("1") && h.equals("1") && an.equals("1") && l.equals("0")) {
                        estado_niveles="Regular";
                    }else
                    if(a.equals("1") && f.equals("0") && h.equals("1") && an.equals("1") && l.equals("0")) {
                        estado_niveles="Malo";
                    }


                    String estado_luces="";

                    if(ec.equals("1") && ecr.equals("1") && ea.equals("1") && ein.equals("1") && ed.equals("1") && er.equals("1")) {
                        estado_luces="Bueno";
                    }else
                    if(ec.equals("1") && ecr.equals("1") && ea.equals("0") && ein.equals("0") && ed.equals("1") && er.equals("1")) {
                        estado_luces="Regular";
                    }else
                    if(ec.equals("1") && ecr.equals("1") && ea.equals("1") && ein.equals("1") && ed.equals("0") && er.equals("0")) {
                        estado_luces="Regular";
                    }




                */