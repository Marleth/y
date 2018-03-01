package com.gtm.itinerario;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class viaticosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton bgasto,bfactura,bhistorial;
    private ProgressDialog progressDialog;
   static String id_cond="",curp_conductor="";
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            final String pref_viaticos=getIntent().getStringExtra("pref_viaticos");
            final String tgasto=getIntent().getStringExtra("tgasto");
            final String concep=getIntent().getStringExtra("concep");
            final String fecha=getIntent().getStringExtra("pref_viaticos");
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

            final String pref_via=getIntent().getStringExtra("pref_via");
            final String   id_vehiculo  = getIntent().getStringExtra ("id_vehiculo");
            final String id_conductor = getIntent().getStringExtra( "id_conductor");
            final String ec=getIntent().getStringExtra("ec");
            final String ecr=getIntent().getStringExtra("ecr");
            final String ea=getIntent().getStringExtra("ea");
            final String ein=getIntent().getStringExtra("ein");
            final String ed=getIntent().getStringExtra("ed");
            final String er=getIntent().getStringExtra("er");
            final String id_supervisor=getIntent().getStringExtra("id_supervisor");
            final String curp_conducto=getIntent().getStringExtra("curp_conducto");
            final String observaciones= getIntent().getStringExtra( "observaciones");
            progressDialog.dismiss();
            try {
                Intent i = new Intent(viaticosActivity.this,Gastos.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("pref_via",pref_via);
                i.putExtra("placas",placas)
                        .putExtra("polizaS",  polizaS)
                        .putExtra("Cfisico",Cfisico)
                        .putExtra("Cconta",Cconta)
                        .putExtra("nPoliza ",nPoliza )
                        .putExtra("campa", campa)
                        .putExtra("exp",exp )
                        .putExtra("venc",venc)
                        .putExtra("tarCir",tarCir)
                        .putExtra("curp_conducto",curp_conducto)
                        .putExtra("observaciones",observaciones)
                        .putExtra("id_vehiculo", id_vehiculo)
                        .putExtra("id_conductor", id_cond);

                //Datos Conductor
                i.putExtra("licenciaO", licenciaO)
                        .putExtra("aptitud",aptitud)
                        .putExtra("bitacora", bitacora)
                        .putExtra("tl", tl)
                        .putExtra("expedlic", expedlic)
                        .putExtra("venclic", venclic)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);
                i.putExtra("pref_via",pref_via);

                //Datos Vehicular
                i.putExtra("ejesli",ejesli)
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
                startActivity(i);
                finish();
            } catch (Exception ex) {
            }


        }
    };

    @SuppressLint("HandlerLeak")
    public Handler handlerError = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progressDialog.dismiss();
            String message = null;
            try {
                message = (String)msg.obj;
            } catch (Exception ex) {
                message = "Usuario no autorizado";
            } finally {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(viaticosActivity.this);
                builder.setTitle("Importante");
                builder.setMessage(message);
                builder.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.cancel();

                            }
                        }
                );

                builder.create();
                builder.show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaticos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String pref_viaticos=getIntent().getStringExtra("pref_viaticos");
        final String tgasto=getIntent().getStringExtra("tgasto");
        final String concep=getIntent().getStringExtra("concep");
        final String fecha=getIntent().getStringExtra("pref_viaticos");
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

        final String pref_via=getIntent().getStringExtra("pref_via");
        final String   id_vehiculo  = getIntent().getStringExtra ("id_vehiculo");
        final String id_conductor = getIntent().getStringExtra( "id_conductor");
        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");
        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");
        final String id_supervisor=getIntent().getStringExtra("id_supervisor");
        final String curp_conducto=getIntent().getStringExtra("curp_conducto");
        final String observaciones= getIntent().getStringExtra( "observaciones");


        bfactura = (ImageButton) findViewById(R.id.imbFac);
        bgasto = (ImageButton) findViewById(R.id.imbGasto);
        bhistorial=(ImageButton)findViewById(R.id.imbHis);
        final Activity activity = this;

        if( pref_viaticos==null) {

            bfactura.setEnabled(false);
            bhistorial.setEnabled(false);
            bfactura.setBackgroundResource(R.drawable.deshabilitar);
            bhistorial.setBackgroundResource(R.drawable.deshabilitar);


        } else if( pref_viaticos.equals("2")) {
            bgasto.setEnabled(false);
            bhistorial.setEnabled(false);

            bgasto.setBackgroundResource(R.drawable.deshabilitar);
            bhistorial.setBackgroundResource(R.drawable.deshabilitar);
            bfactura.setBackgroundResource(R.drawable.edittext);
        } else if(pref_viaticos.equals("1")) {
            bfactura.setEnabled(false);
            bgasto.setEnabled(false);
            bhistorial.setEnabled(true);

            bgasto.setBackgroundResource(R.drawable.deshabilitar);

             bhistorial.setBackgroundResource(R.drawable.edittext);
        }
        bfactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(viaticosActivity.this, Facturacion.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
                        .putExtra("observaciones",observaciones)
                        .putExtra("id_vehiculo", id_vehiculo)
                        .putExtra("id_conductor", id_conductor);

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
                finish();


            }
        });



        bgasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(viaticosActivity.this,Gastos.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
/*Inicio                final String pref_via=getIntent().getStringExtra("pref_via");
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Escanear el codigo qr de la placa");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
fin*/

            }
        });


        bhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final android.support.v7.app.AlertDialog.Builder builder1 =
                        new android.support.v7.app.AlertDialog.Builder(viaticosActivity.this);

                final CharSequence[] items = new CharSequence[2];

                items[0] = "Gastos";
                items[1] = "Facturacion";

                //Agrego un icono
                builder1.setIcon(R.drawable.historia);


                //Cambio el color del titulo del AlertDialog
                builder1.setTitle(Html.fromHtml("<font color='#269501'>HISTORIAL</font>"));
                builder1.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });


                builder1.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int posicion) {


                        if (posicion == 0) {

                            Intent i = new Intent(viaticosActivity.this, Hfacturacion.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            startActivity(i);
                            finish();
                        }

                        if (posicion == 1) {
                            Intent i = new Intent(viaticosActivity.this, HGastos.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


                            startActivity(i);
                            finish();
                        }
                    }
                });

                builder1.show();


            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);



        bfactura = (ImageButton) findViewById(R.id.imbFac);
        bgasto = (ImageButton) findViewById(R.id.imbGasto);
        bhistorial=(ImageButton)findViewById(R.id.imbHis);
        final Activity activity = this;

        final Synchronization sincronizar;
        sincronizar = new Synchronization(this);
        String curp;
        final String curp_super="" ;

        if(result != null) if (result.getContents() == null) {
            Log.d("MainActivity", "Escaner cancelado");
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        } else {
            final String resultados = String.valueOf(result.getContents());

         progressDialog = ProgressDialog.show(viaticosActivity.this, "", "Validando Usuario...");
            new Thread() {

                public void run() {
                    String msg = "";
                    try {
                        int connectionStatus = sincronizar.getStatusConnection();
                        if (   connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK ||
                                connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                            sincronizar.Synchronize();
                        } else {
                            Log.e("tag", "Error de conexión");
                            Message message = handlerError.obtainMessage();
                            message.obj = "Error al intentar sincronizar.";
                            handlerError.sendMessage(message);
                        }
                        ArrayList<ArrayList<String>> id_super = sincronizar.ExecuteSelect("Select id_conductor,curp_conductor from Conductor WHERE curp_conductor = '" + resultados.toString() + "' ", null);

                        if (id_super.size() == 1) {

                            id_cond= id_super.get(0).get(0);
                            curp_conductor =id_super.get(0).get(1);
                        }

                        id_super = null;
                        if (resultados.toString().equals(curp_conductor)) {
                            handler.sendEmptyMessage(1);



                        } else {
                            Message message = handlerError.obtainMessage();
                            message.obj = "Usuario no autorizado";
                            handlerError.sendMessage(message);
                        }

                    } catch (Exception e) {
                        //   Log.e("tag", e.getMessage());
                        Message message = handlerError.obtainMessage();
                        message.obj = "Error al obtener datos del usuario.";
                        handlerError.sendMessage(message);

                    }
                }
            }.start();


        }
    }
    @Override
    public void onBackPressed() {
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


        final String pref_via ="4";
        Intent i=new Intent(viaticosActivity.this,principalActivity.class);
        i.putExtra("placas",placas)
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
        i.putExtra("licenciaO", licenciaO)
                .putExtra("aptitud",aptitud)
                .putExtra("bitacora", bitacora)
                .putExtra("tl", tl)
                .putExtra("expedlic", expedlic)
                .putExtra("venclic", venclic)
                .putExtra("num_licencia",num_licencia)
                .putExtra("e", e);
        i.putExtra("pref_via",pref_via);

        //Datos Vehicular
        i.putExtra("ejesli",ejesli)
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

        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.viaticos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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

        final String id_supervisor=getIntent().getStringExtra("id_supervisor");

        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");
        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");


        final String pref_via=getIntent().getStringExtra("pref_via");

        int id = item.getItemId();

        if (id == R.id.inicio) {
            Intent i=new Intent(viaticosActivity.this,principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            i.putExtra("placas",placas)
                    .putExtra("polizaS",  polizaS)
                    .putExtra("Cfisico",Cfisico)
                    .putExtra("Cconta",Cconta)
                    .putExtra("nPoliza ",nPoliza )
                    .putExtra("campa", campa)
                    .putExtra("exp",exp )
                    .putExtra("venc",venc)
                    .putExtra("tarCir",tarCir);

            //Datos Conductor
            i.putExtra("licenciaO", licenciaO)
                    .putExtra("aptitud",aptitud)
                    .putExtra("bitacora", bitacora)
                    .putExtra("tl", tl)
                    .putExtra("expedlic", expedlic)
                    .putExtra("venclic", venclic)
                    .putExtra("num_licencia",num_licencia)
                    .putExtra("e", e);
            i.putExtra("pref_via",pref_via);

            //Datos Vehicular
            i.putExtra("ejesli",ejesli)
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
                    .putExtra("an",an)
                    .putExtra("id_supervisor",id_supervisor)
                    .putExtra("l",l);
            i.putExtra("pref_via",pref_via);

            startActivity(i);
            finish();
        } else if (id == R.id.vehicular) {
            Intent v=new Intent(viaticosActivity.this,vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(v);
            finish();
        } else if (id == R.id.documentacion) {
            Intent d=new Intent(viaticosActivity.this,DocActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


            startActivity(d);
            finish();
        } else if (id == R.id.carga) {
            Intent c=new Intent(viaticosActivity.this,cargaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(c);
            finish();
        } else if (id == R.id.salir) {
            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(viaticosActivity.this);
            builder.setMessage("Deseas salir")
                    .setTitle("Advertencia")
                    .setCancelable(false)
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                    .setPositiveButton("Continuar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();

        }else if(id == R.id.cerrar) {

            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(viaticosActivity.this);
            builder.setMessage("Deseas cerrar sesion")
                    .setTitle("Advertencia")
                    .setCancelable(false)
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                    .setPositiveButton("Continuar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent cerrar = new Intent(viaticosActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(cerrar);
                                    finish();
                                }
                            });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();
        }
        else if (id == R.id.localizador) {
            Intent c=new Intent(viaticosActivity.this, LocalizacionActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(c);
            finish();
        }
        else if (id == R.id.configuracin) {

        } else if (id == R.id.uso) {

        }else if (id == R.id.condiciones) {

        } else if (id == R.id.soporte) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
