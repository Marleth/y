package com.gtm.itinerario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class principalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static String id_vehiculo="",num_placas="",id_conductor="",num_conductor="";

    private Button bGuardar , enviar_inf;
    private ImageButton documentacion,vehiculo,carga,viaticos;

    private ProgressDialog progressDialog;


    @SuppressLint("HandlerLeak")
    public Handler handlerError = new Handler() {
        public void handleMessage(android.os.Message msg) {

            String message = null;
            try {
                message = (String)msg.obj;
            } catch (Exception ex) {
                message = "Usuario no autorizado";
            } finally {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(principalActivity.this);
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


        private int positionNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String  nPoliza= getIntent().getStringExtra( "nPoliza");
        final String campa = getIntent().getStringExtra( "campa");
        final String   exp = getIntent().getStringExtra( "exp");
        final String  venc  = getIntent().getStringExtra( "venc");
        final String tarCir= getIntent().getStringExtra( "tarCir");
        final String curp_conducto= getIntent().getStringExtra( "curp_conducto");
        final String id_supervisor= getIntent().getStringExtra( "id_supervisor");
        final String observaciones= getIntent().getStringExtra( "observaciones");
        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String expedlic = getIntent().getStringExtra("expedlic");
        final String venclic = getIntent().getStringExtra("ve");
        final String e=getIntent().getStringExtra("e");
        final String ve=getIntent().getStringExtra("ve");
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
        final String pref_via=getIntent().getStringExtra("pref_via");
         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menuNav=navigationView.getMenu();


        //SharedPreferences pref =getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        // final String pref_via = pref.getString("pref_via", "pref_via");

        bGuardar=(Button)findViewById(R.id.fianl);
        bGuardar.setVisibility(View .INVISIBLE);

        documentacion=(ImageButton )findViewById(R.id.imageButton2);
        vehiculo=(ImageButton )findViewById(R.id.imageButton);

        carga=(ImageButton )findViewById(R.id.imageButton4);
        viaticos=(ImageButton )findViewById(R.id.imageButton3);

        final Activity activity = this;
        if(pref_via==null ) {
            MenuItem menu_Vehiculos = menuNav.findItem(R.id.vehiculos);
            menu_Vehiculos.setEnabled(false);

            MenuItem menu_carga=menuNav.findItem(R.id.carga);
            menu_carga.setEnabled(false);

            MenuItem menu_viaticos=menuNav.findItem(R.id.viaticos);
            menu_viaticos.setEnabled(true);


            documentacion.setEnabled(true);
            vehiculo.setEnabled(false);
            carga.setEnabled(false);
            viaticos.setEnabled(false);
            vehiculo.setBackgroundResource(R.drawable.deshabilitar);
            carga.setBackgroundResource(R.drawable.deshabilitar);
            viaticos.setBackgroundResource(R.drawable.deshabilitar);

            documentacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*Inicio                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt("Escanear el codigo qr de la placa");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
Fin*/

                    Intent docu = new Intent(principalActivity.this, DocActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(docu);
                } });
        }

        else if(   pref_via.equals("2")) {

            MenuItem menu_documentacion = menuNav.findItem(R.id.documentacion);
            menu_documentacion.setEnabled(false);

            MenuItem menu_carga=menuNav.findItem(R.id.carga);
            menu_carga.setEnabled(false);

            MenuItem menu_viaticos=menuNav.findItem(R.id.viaticos);
            menu_viaticos.setEnabled(false);

            vehiculo.setEnabled(true);
            carga.setEnabled(false);
            viaticos.setEnabled(false);
            documentacion.setEnabled(false);

            documentacion.setBackgroundResource(R.drawable.deshabilitar);
            carga.setBackgroundResource(R.drawable.deshabilitar);
            viaticos.setBackgroundResource(R.drawable.deshabilitar);

            vehiculo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent veh = new Intent(principalActivity.this, vehicularActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    //Datos vehiculo
                    veh.putExtra("placas",placas)
                            .putExtra("polizaS",  polizaS)
                            .putExtra("Cfisico",Cfisico)
                            .putExtra("Cconta",Cconta)
                            .putExtra("nPoliza ",nPoliza )
                            .putExtra("campa", campa)
                            .putExtra("exp",exp )
                            .putExtra("venc",venc)
                            .putExtra("tarCir",tarCir)
                            .putExtra("ve",ve)
                    .putExtra("id_supervisor",id_supervisor)
                            .putExtra("curp_conducto",curp_conducto)
                            .putExtra("observaciones",observaciones);

                    //Datos Conductor
                    veh.putExtra("licenciaO", licenciaO)
                            .putExtra("aptitud",aptitud)
                            .putExtra("bitacora", bitacora)
                            .putExtra("tl", tl)
                            .putExtra("expedlic", expedlic)
                            .putExtra("venclic", venclic)
                            .putExtra("num_licencia",num_licencia)
                            .putExtra("e", e);
                    startActivity(veh);

                }
            });
        }
        else if (pref_via.equals("3")) {
            MenuItem menu_docu = menuNav.findItem(R.id.documentacion);
            menu_docu.setEnabled(false);
            MenuItem menu_vehicular=menuNav.findItem(R.id.vehiculos);
            menu_vehicular.setEnabled(false);

            MenuItem menu_via=menuNav.findItem(R.id.viaticos);
            menu_via.setEnabled(false);



            carga.setEnabled(true);
            vehiculo.setEnabled(false);
                  viaticos.setEnabled(false);
            documentacion.setEnabled(false);

            documentacion.setBackgroundResource(R.drawable.deshabilitar);
            vehiculo.setBackgroundResource(R.drawable.deshabilitar);
            viaticos.setBackgroundResource(R.drawable.deshabilitar);

            carga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent vi = new Intent(principalActivity.this, cargaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //Datos vehiculo
                    vi.putExtra("placas",placas)
                            .putExtra("polizaS",  polizaS)
                            .putExtra("Cfisico",Cfisico)
                            .putExtra("Cconta",Cconta)
                            .putExtra("nPoliza ",nPoliza )
                            .putExtra("campa", campa)
                            .putExtra("exp",exp )
                            .putExtra("venc",venc)
                            .putExtra("id_supervisor",id_supervisor)

                            .putExtra("tarCir",tarCir)
                            .putExtra("ve",ve)
                            .putExtra("curp_conducto",curp_conducto)
                            .putExtra("observaciones",observaciones);

                    //Datos Conductor
                    vi.putExtra("licenciaO", licenciaO)
                            .putExtra("aptitud",aptitud)
                            .putExtra("bitacora", bitacora)
                            .putExtra("tl", tl)
                            .putExtra("expedlic", expedlic)
                            .putExtra("venclic", venclic)
                            .putExtra("num_licencia",num_licencia)
                            .putExtra("e", e);

                    //Datos Vehicular
                    vi.putExtra("ejesli",ejesli)
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
                            .putExtra("l",l);
                    startActivity(vi);
                }
            });
        }
        else if(pref_via.equals("4")){
            MenuItem menu_docu = menuNav.findItem(R.id.documentacion);
            menu_docu.setEnabled(false);
            MenuItem menu_vehiculos = menuNav.findItem(R.id.vehiculos);
            menu_vehiculos.setEnabled(false);


            MenuItem menu_carga=menuNav.findItem(R.id.carga);
            menu_carga.setEnabled(false);



            viaticos.setEnabled(true);
            documentacion.setEnabled(false);
            vehiculo.setEnabled(false);
            carga.setEnabled(false);

            documentacion.setBackgroundResource(R.drawable.deshabilitar);
            carga.setBackgroundResource(R.drawable.deshabilitar);
            vehiculo.setBackgroundResource(R.drawable.deshabilitar);

            viaticos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent vi=new Intent(principalActivity.this,viaticosActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    vi.putExtra("placas",placas)
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
                            .putExtra("id_supervisor",id_supervisor)
                    .putExtra("pref_via",pref_via);

                    //Datos Conductor
                    vi.putExtra("licenciaO", licenciaO)
                            .putExtra("aptitud",aptitud)
                            .putExtra("bitacora", bitacora)
                            .putExtra("tl", tl)
                            .putExtra("expedlic", expedlic)
                            .putExtra("venclic", venclic)
                            .putExtra("num_licencia",num_licencia)
                            .putExtra("e", e)
                            .putExtra("ve",ve);

                    //Datos Vehicular
                    vi.putExtra("ejesli",ejesli)
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
                            .putExtra("l",l);

                    startActivity(vi);
                }
            });
        }else if(pref_via.equals("5")){
            viaticos.setEnabled(false);
            documentacion.setEnabled(false);
            vehiculo.setEnabled(false);
            carga.setEnabled(false);
            bGuardar.setVisibility(View.VISIBLE);

            documentacion.setBackgroundResource(R.drawable.deshabilitar);
            carga.setBackgroundResource(R.drawable.deshabilitar);
            vehiculo.setBackgroundResource(R.drawable.deshabilitar);
            viaticos.setBackgroundResource(R.drawable.deshabilitar);


            bGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*Inicio
                    final Activity activity = principalActivity.this;
                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt("Escanear el codigo qr de conductor ");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
Fin*/

                }
            });
        }




    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final String posiVeh=getIntent().getStringExtra("posi");
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
        final String venclic = getIntent().getStringExtra("ve");
        final String e=getIntent().getStringExtra("e");
        final String ve=getIntent().getStringExtra("ve");
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

        final String placas_final = getIntent().getStringExtra("placas");

        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");

        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");
        final String scanner=getIntent().getStringExtra("scanner");

        final String observaciones= getIntent().getStringExtra( "observaciones");
        final String id_supervisor= getIntent().getStringExtra( "id_supervisor");

        final Synchronization sincronizar;
        sincronizar = new Synchronization(this);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) if (result.getContents() == null) {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        } else {
/* Inicio comente
            progressDialog = ProgressDialog.show(principalActivity.this, "", "Validando Usuario...");


            final String resultados = String.valueOf(result.getContents());


            new Thread() {
                public void run() {
                    String msg = "";
                    try {
                        int connectionStatus = sincronizar.getStatusConnection();
                        if (connectionStatus == Operation.CONNECTION_STATUS_BOOTH_OK ||
                            connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK ||
                            connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {

                            sincronizar.Synchronize();
                        } else {
                            Log.e("tag", "Error de conexión");
                            Message message = handlerError.obtainMessage();
                            message.obj = "Error al intentar sincronizar.";
                            handlerError.sendMessage(message);
                        }

                        ArrayList<ArrayList<String>> id_vehi = sincronizar.ExecuteSelect
                                ("Select id_vehiculo,placas from  Vehiculo WHERE placas = '" + resultados.toString() + "' ", null);

                        if (id_vehi.size() == 1) {

                            id_vehiculo = id_vehi.get(0).get(0);
                            num_placas=id_vehi.get(0).get(1);
                        }
                        id_vehi = null;
                            //********************** placas
                            if(resultados.toString().equals(num_placas)) {

                                String placas=resultados;
                                Intent docu = new Intent(principalActivity.this, DocActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                docu.putExtra("id_supervisor",id_supervisor);
                                //Datos vehiculo
                                docu.putExtra("id_vehiculo",   id_vehiculo )
                                        .putExtra("polizaS", polizaS)
                                        .putExtra("Cfisico", Cfisico)
                                        .putExtra("Cconta", Cconta)
                                        .putExtra("nPoliza ", nPoliza)
                                        .putExtra("campa", campa)
                                        .putExtra("exp", exp)
                                        .putExtra("venc", venc)
                                        .putExtra("tarCir", tarCir)

                                        .putExtra("observaciones", observaciones);

                                //Datos Conductor
                                docu.putExtra("licenciaO", licenciaO)
                                        .putExtra("aptitud", aptitud)
                                        .putExtra("bitacora", bitacora)
                                        .putExtra("tl", tl)
                                        .putExtra("expedlic", expedlic)
                                        .putExtra("venclic", venclic)
                                        .putExtra("num_licencia", num_licencia)
                                        .putExtra("e", e)
                                        .putExtra("ve", ve);
                                startActivity(docu);

                            }else {


                                ArrayList<ArrayList<String>> id_con = sincronizar.ExecuteSelect
                                        ("Select id_conductor,curp_conductor from Conductor WHERE curp_conductor = '" + resultados.toString() + "' ", null);
                                if (id_con.size() == 1) {
                                    id_conductor = id_con.get(0).get(0);
                                    num_conductor = id_con.get(0).get(1);
                                }

                                if (resultados.equals(num_conductor)) {

                                    String curp_conducto = resultados;
                                    curp_conducto = resultados.toString();
                                    Intent vi = new Intent(principalActivity.this, Reporte_General.class);

                                    vi.putExtra("id_vehiculo", id_vehiculo)
                                            .putExtra("polizaS", polizaS)
                                            .putExtra("Cfisico", Cfisico)
                                            .putExtra("Cconta", Cconta)
                                            .putExtra("nPoliza ", nPoliza)
                                            .putExtra("campa", campa)
                                            .putExtra("exp", exp)
                                            .putExtra("venc", venc)
                                            .putExtra("tarCir", tarCir)
                                            .putExtra("id_conductor", id_conductor)
                                            .putExtra("id_supervisor", id_supervisor)
                                            .putExtra("observaciones", observaciones);

//Datos Conductor
                                    vi.putExtra("licenciaO", licenciaO)
                                            .putExtra("aptitud", aptitud)
                                            .putExtra("bitacora", bitacora)
                                            .putExtra("tl", tl)
                                            .putExtra("expedlic", expedlic)
                                            .putExtra("venclic", venclic)
                                            .putExtra("num_licencia", num_licencia)
                                            .putExtra("e", e)
                                            .putExtra("ve", ve);

//Datos Vehicular
                                    vi.putExtra("ejesli", ejesli)
                                            .putExtra("tipo_vehi", tipo_vehi)
                                            .putExtra("ejesp", ejesp)
                                            .putExtra("es", es)
                                            .putExtra("r", r)
                                            .putExtra("ec", ec)
                                            .putExtra("ecr", ecr)
                                            .putExtra("ea", ea)
                                            .putExtra("ein", ein)
                                            .putExtra("ed", ed)
                                            .putExtra("er", er)

                                            .putExtra("a", a)
                                            .putExtra("f", f)
                                            .putExtra("h", h)
                                            .putExtra("an", an)
                                            .putExtra("l", l);

                                    startActivity(vi);
                                }
                                else {
                                    Message message = handlerError.obtainMessage();
                                    message.obj = "Usuario no autorizado";
                                    handlerError.sendMessage(message);
                                }
                            }


                    }catch (Exception e) {

                        Message message = handlerError.obtainMessage();
                        message.obj = "Error al obtener datos del usuario.";
                        handlerError.sendMessage(message);

                    }
                }
            }.start();

Fin comente*/
            Intent docu = new Intent(principalActivity.this, DocActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            docu.putExtra("id_supervisor",id_supervisor);
            //Datos vehiculo
            docu.putExtra("id_vehiculo",   id_vehiculo )
                    .putExtra("polizaS", polizaS)
                    .putExtra("Cfisico", Cfisico)
                    .putExtra("Cconta", Cconta)
                    .putExtra("nPoliza ", nPoliza)
                    .putExtra("campa", campa)
                    .putExtra("exp", exp)
                    .putExtra("venc", venc)
                    .putExtra("tarCir", tarCir)

                    .putExtra("observaciones", observaciones);

            //Datos Conductor
            docu.putExtra("licenciaO", licenciaO)
                    .putExtra("aptitud", aptitud)
                    .putExtra("bitacora", bitacora)
                    .putExtra("tl", tl)
                    .putExtra("expedlic", expedlic)
                    .putExtra("venclic", venclic)
                    .putExtra("num_licencia", num_licencia)
                    .putExtra("e", e)
                    .putExtra("ve", ve);
            startActivity(docu);

            }
        }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final String posiVeh=getIntent().getStringExtra("posi");

        final String id_supervisor = getIntent().getStringExtra( "id_supervisor");

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
        final String venclic = getIntent().getStringExtra("ve");
        final String e=getIntent().getStringExtra("e");
        final String ve=getIntent().getStringExtra("ve");
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
        final String pref_via=getIntent().getStringExtra("pref_via");

        final String observaciones= getIntent().getStringExtra( "observaciones");

        int id  = item.getItemId();
           if (id == R.id.documentacion) {
               final Activity activity =principalActivity.this;
/*Inicio             IntentIntegrator integrator = new IntentIntegrator(activity);
               integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
               integrator.setPrompt("Escanear el codigo qr de la placa ");
               integrator.setCameraId(0);
               integrator.setBeepEnabled(false);
               integrator.setBarcodeImageEnabled(false);
               integrator.initiateScan();
Fin*/

        } else if (id == R.id.vehiculos) {
            Intent v=new Intent(principalActivity.this,vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

               //Datos vehiculo
               v.putExtra("placas",placas)
                       .putExtra("polizaS",  polizaS)
                       .putExtra("Cfisico",Cfisico)
                       .putExtra("Cconta",Cconta)
                       .putExtra("nPoliza ",nPoliza )
                       .putExtra("campa", campa)
                       .putExtra("exp",exp )
                       .putExtra("venc",venc)
                       .putExtra("tarCir",tarCir)
                       .putExtra("observaciones",observaciones)
                       .putExtra("ve",ve);

               //Datos Conductor
               v.putExtra("licenciaO", licenciaO)
                       .putExtra("aptitud",aptitud)
                       .putExtra("bitacora", bitacora)
                       .putExtra("tl", tl)
                       .putExtra("expedlic", expedlic)
                       .putExtra("venclic", venclic)
                       .putExtra("num_licencia",num_licencia)
                       .putExtra("e", e)
                       .putExtra("id_supervisor",id_supervisor)
               .putExtra("pref_via",pref_via);
               startActivity(v);


            finish();
        } else if (id == R.id.carga) {
            Intent c = new Intent(principalActivity.this, cargaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

               //Datos vehiculo
               c.putExtra("placas",placas)
                       .putExtra("polizaS",  polizaS)
                       .putExtra("Cfisico",Cfisico)
                       .putExtra("Cconta",Cconta)
                       .putExtra("nPoliza ",nPoliza )
                       .putExtra("campa", campa)
                       .putExtra("exp",exp )
                       .putExtra("venc",venc)
                       .putExtra("tarCir",tarCir)
                       .putExtra("ve",ve)
                       .putExtra("id_supervisor",id_supervisor)
                       .putExtra("observaciones",observaciones);

               //Datos Conductor
               c.putExtra("licenciaO", licenciaO)
                       .putExtra("aptitud",aptitud)
                       .putExtra("bitacora", bitacora)
                       .putExtra("tl", tl)
                       .putExtra("expedlic", expedlic)
                       .putExtra("venclic", venclic)
                       .putExtra("num_licencia",num_licencia)
                       .putExtra("e", e);

               //Datos Vehicular
               c.putExtra("ejesli",ejesli)
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
                       .putExtra("l",l);

            startActivity(c);
            finish();
        } else if (id == R.id.viaticos) {
           Intent vi=new Intent(principalActivity.this,Facturacion.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
               vi.putExtra("placas",placas)
                       .putExtra("polizaS",  polizaS)
                       .putExtra("Cfisico",Cfisico)
                       .putExtra("Cconta",Cconta)
                       .putExtra("nPoliza ",nPoliza )
                       .putExtra("campa", campa)
                       .putExtra("exp",exp )
                       .putExtra("venc",venc)
                       .putExtra("tarCir",tarCir)
                       .putExtra("id_supervisor",id_supervisor)
                       .putExtra("observaciones",observaciones);

               //Datos Conductor
               vi.putExtra("licenciaO", licenciaO)
                       .putExtra("aptitud",aptitud)
                       .putExtra("bitacora", bitacora)
                       .putExtra("tl", tl)
                       .putExtra("expedlic", expedlic)
                       .putExtra("venclic", venclic)
                       .putExtra("num_licencia",num_licencia)
                       .putExtra("e", e)
                       .putExtra("ve",ve);

               //Datos Vehicular
               vi.putExtra("ejesli",ejesli)
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
                       .putExtra("l",l)
                       .putExtra("pref_via",pref_via);
            startActivity(vi);
            finish();
        } else if (id == R.id.salir) {
            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(principalActivity.this);
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

            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(principalActivity.this);
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
                                    //Borramos el usuario almacenado en preferencias y volvemos a la pantalla de login
                                    SharedPreferences settings = getSharedPreferences("Mipreferencia", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor.putString("user", "");
                                    editor.putString("pass", "");
                                    //Confirmamos el almacenamiento.
                                    editor.commit();

                                    Intent vi=new Intent(principalActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
                                    startActivity(vi);
                                    finish();


                                }
                            });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();

        }
        else if (id == R.id.localizador) {
            Intent c=new Intent(principalActivity.this, LocalizacionActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );

            startActivity(c);
            finish();
        }
        else if (id == R.id.configuracin) {
            Intent c=new Intent(principalActivity.this, HistorialActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity(c);

        } else if (id == R.id.uso) {

        }else if (id == R.id.condiciones) {

        } else if (id == R.id.soporte) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
