package com.gtm.itinerario;

import android.app.ActionBar;
 import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class  vehicularActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener {

    ImageButton general,neumaticos,niveles1,luces1;

    //spinner en el dialogo

    String[] tipo = {"","T1","T2","T3","T4","T5","T6","T7","T8","T9"};
    String[] cant = {"","1","2"};
    String[] ejes = {"","1","2","3","4","5","6","7","8"};

    String[] repuesto={"","Si","No"};
    String[] estado={"","Bueno","Regular","Malo"};
    static String descrip="", tipo_vehi="",ep="",ejesp="", ejesli="",es="",r="",f="1",
            a="1 ", h="1" ,an="1",l="1",ec="1",ecr="1",ea="1",ein="1",ed="1",er="1", estadoss="" , repuestoss="";
    Button guardar,cancelar,guardarl,cancelarl;
    CheckBox Aceite,Freno,hidraulica,anti,limpia,carretera,cruce,antiniebla,intermitente,direccionales,reversa;
    Button g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String  nPoliza= getIntent().getStringExtra( "nPoliza");
        final String campa = getIntent().getStringExtra( "campa");
        final String   exp = getIntent().getStringExtra( "exp");
        final String  venc  = getIntent().getStringExtra( "venc");



        final String observaciones= getIntent().getStringExtra( "observaciones");


        final String tarCir= getIntent().getStringExtra( "tarCir");
        //Datos Conductor
        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String ve= getIntent().getStringExtra("ve");
        final String id_supervisor= getIntent().getStringExtra("id_supervisor");
        final String curp_conducto= getIntent().getStringExtra("curp_conducto");
        final String e=getIntent().getStringExtra("e");


        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        neumaticos=(ImageButton)findViewById(R.id.imageButton7);
        niveles1=(ImageButton)findViewById(R.id.imageButton9);
        luces1=(ImageButton)findViewById(R.id.imageButton10);

        neumaticos.setEnabled(true);
        neumaticos.setBackgroundResource(R.drawable.edittext);
        niveles1.setEnabled(false);
        luces1.setEnabled(false);


         g=(Button)findViewById(R.id.button17);
        //g.setVisibility(View.INVISIBLE);

        g.setVisibility(View.INVISIBLE);

        neumaticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        final android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);

                final CharSequence[] items = new CharSequence[2];

                items[0] = "Rueda de repuesto";
                items[1] = "Estado de los neumaticos";


                //Cambio el color del titulo del AlertDialog

                builder1.setTitle(Html.fromHtml("<font color='#269501'>NEUMATICOS</font>"));

                builder1.setIcon(getResources().getDrawable(R.drawable.ic_neumaticos))
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int posicion) {
                                //Repuesto

                                if(posicion ==0){

                                    final ArrayAdapter<String> adp = new ArrayAdapter<String>(vehicularActivity.this,
                                            android.R.layout.simple_spinner_item,repuesto);

                                    final Spinner repu= new Spinner(vehicularActivity.this);
                                    repu.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                    repu.setAdapter(adp);

                                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                                    builder.setMessage("Rueda de repuesto");



                                    repu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                            //0,1
                                            repuestoss =parent.getItemAtPosition(position).toString();

                                            if (repuestoss.equals("Si")) {
                                                r="1";
                                            }else{
                                                r="0";
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    }  );


                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            builder1.show();
                                        }
                                    })
                                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            });
                                    builder.setView(repu);
                                    builder.create().show();
                                }
                                //Estado de los neumaticos

                                if(posicion ==1){
                                    final ArrayAdapter<String> adp = new ArrayAdapter<String>(vehicularActivity.this,
                                            android.R.layout.simple_spinner_item, estado);

                                    final Spinner neumaticos= new Spinner(vehicularActivity.this);
                                    neumaticos.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                    neumaticos.setAdapter(adp);


                                    neumaticos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                                            //0,1,2//
                                            estadoss =parent.getItemAtPosition(position).toString();

                                            if ( estadoss=="Bueno") {
                                                es="0";
                                            }
                                            if ( estadoss=="Regular") {
                                                es="1";
                                            }
                                            if ( estadoss=="Malo"){
                                                es = "2";
                                            }


                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    }  );


                                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                                    builder.setMessage("Estado de los neumaticos");
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            builder1.show();
                                        }
                                    })
                                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    builder1.show();
                                                }
                                            });
                                    builder.setView(neumaticos);

                                    builder.create().show();
                                }

                            }

                        });
               /* final EditText input = new EditText(getApplicationContext());
                builder1.setView(input);
                 input.setTextColor(Integer.parseInt("#000000"));
                */builder1.setPositiveButton("Enviar informacion", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if(es.equals("") && r.equals("") ){
                            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                            builder.setMessage("Llenar los campos");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    builder1.show();
                                }
                            });
                            builder.create().show();
                        }else
                        if(es.equals("") && !r.equals("") ){
                            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                            builder.setMessage("Llenar el campo="+"\n"+"  *  Estado de los neumaticos");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    builder1.show();
                                }
                            });
                            builder.create().show();
                        }else

                        if(!es.equals("") && r.equals("") ){
                            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                            builder.setMessage("Llenar el campo="+"\n"+"  *  Rueda de repuesto");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    builder1.show();
                                }
                            });
                            builder.create().show();
                        }else
                        if(!es.equals("") && !r.equals("")) {


                            niveles1.setEnabled(true);
                            neumaticos.setEnabled(false);
                            niveles1.setBackgroundResource(R.drawable.edittext);
                            neumaticos.setBackgroundResource(R.drawable.deshabilitar);
                        }

                    }
                })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                builder1.show();
            }
        });

        niveles1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder nivel = new AlertDialog.Builder(vehicularActivity.this);
                final View custom = View.inflate(vehicularActivity.this, R.layout.alertdialog_niveles, null);
                nivel.setView(custom);

                Aceite=(CheckBox)custom.findViewById(R.id.checkBox) ;
                Freno=(CheckBox)custom.findViewById(R.id.checkBox2);
                hidraulica=(CheckBox) custom.findViewById(R.id.checkBox3);
                anti=(CheckBox)custom.findViewById(R.id.checkBox4);
                limpia=(CheckBox)custom.findViewById(R.id.checkBox5);

                guardar=(Button)custom.findViewById(R.id.button13);
                cancelar=(Button)custom.findViewById(R.id.button14);

                final TextView niveles=(TextView)findViewById(R.id.textView27 );

                Aceite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            a ="1";
                        }else{
                            a ="0";
                        }}
                });

                Freno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            f="1";
                        }else{
                            f="0";
                        }

                    }
                });

                hidraulica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            h="1";
                        }else{
                            h="0";
                        }}
                });

                anti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            an="1";
                        }else{
                            an="0";
                        }
                    }
                });


                limpia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            l="1";
                        }else{
                            l="0";
                        }
                    }
                });
                final AlertDialog alertDialog = nivel.create();

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                         luces1.setEnabled(true);
                        niveles1.setEnabled(false);
                        luces1.setBackgroundResource(R.drawable.edittext);
                        niveles1.setBackgroundResource(R.drawable.deshabilitar);

                        alertDialog.dismiss();
                        }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        general.setEnabled(false);
                        general.setBackgroundResource(R.drawable.deshabilitar);

                        Intent i=new Intent(vehicularActivity.this,vehicularActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        alertDialog.dismiss();

                    }
                });



                alertDialog.show();
            }
        });

        luces1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder luces = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
                final View custom = View.inflate(vehicularActivity.this, R.layout.alertdialog_luces, null);
                luces.setView(custom);
                carretera=(CheckBox)custom.findViewById(R.id.checkBox6);
                cruce=(CheckBox)custom.findViewById(R.id.checkBox7);
                antiniebla=(CheckBox)custom.findViewById(R.id.checkBox8);
                intermitente=(CheckBox)custom.findViewById(R.id.checkBox9);
                direccionales=(CheckBox)custom.findViewById(R.id.checkBox10);
                reversa=(CheckBox)custom.findViewById(R.id.checkBox11);

                guardarl=(Button)custom.findViewById(R.id.button10);
                cancelarl=(Button)custom.findViewById(R.id.button15);

                carretera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ec="1";
                        }else
                            ec="0";
                    }
                });
                cruce                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ecr="1";
                        }else{
                            ecr="0";
                        }
                    }
                });
                antiniebla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if(isChecked){
                            ea="1";
                        }else
                            ea="0";
                    }
                });
                intermitente                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ein="";
                        }else{
                            ein="0";
                        }
                    }
                });

                direccionales .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ed="1";
                        }else{
                            ed="0";
                        }
                    }
                });
                reversa                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            er="1";
                        }else{
                            er = "0";
                        }
                    }
                });
                final AlertDialog alertDialog = luces.create();

                guardarl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        g.setVisibility(View.VISIBLE);
                        luces1.setEnabled(false);
                        luces1.setBackgroundResource(R.drawable.deshabilitar);
                        alertDialog.dismiss();

                    }
                });
                cancelarl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        general.setEnabled(false);
                        general.setBackgroundResource(R.drawable.deshabilitar);
                        Intent i=new Intent(vehicularActivity.this,vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });
        final String pref_via="3";

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vehicularActivity.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
                Intent p = new Intent(vehicularActivity.this,principalActivity.class);
                p.putExtra("pref_via",pref_via);
                startActivity(p);
/*Inicio
                Intent i=new Intent(vehicularActivity.this,Datos_generales.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

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
                        .putExtra("curp_conducto",curp_conducto)
                        .putExtra("descrip",descrip)
                        .putExtra( "a",a )
                        .putExtra("f",f)
                        .putExtra("h",h)
                        .putExtra("an",an)
                        .putExtra("l",l)
                        .putExtra("ve",ve)

                        .putExtra("observaciones",observaciones);

                //Datos vehiculo
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
                        .putExtra("ve", ve)
                        .putExtra("id_supervisor", id_supervisor)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);

                startActivity(i);
Fin */
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vehicular, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Vehicular
        final String r = getIntent().getStringExtra("r");
        final String es =getIntent().getStringExtra("es");
        final String ejesli = getIntent().getStringExtra ("ejesli");
        final String tipo_vehi= getIntent().getStringExtra("tipo_vehi");
        final String ejesp= getIntent().getStringExtra("ejesp");

        //Datos vehiculos
        final String  placas = getIntent().getStringExtra( "placas");
        final String polizaS= getIntent().getStringExtra( "polizaS");
        final String Cfisico= getIntent().getStringExtra( "Cfisico");
        final String Cconta = getIntent().getStringExtra( "Cconta");
        final String  nPoliza= getIntent().getStringExtra( "nPoliza ");
        final String campa = getIntent().getStringExtra( "campa");
        final String   exp = getIntent().getStringExtra( "exp");
        final String  venc  = getIntent().getStringExtra( "venc");
        final String tarCir= getIntent().getStringExtra( "tarCi");
        //Datos Conductor
        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String expedlic = getIntent().getStringExtra("expedlic");
        final String venclic = getIntent().getStringExtra("venclic");
        final String e=getIntent().getStringExtra("e");
        final String id_supervisor=getIntent().getStringExtra("id_supervisor");
        final String pref_via=getIntent().getStringExtra("pref_via");
  int id = item.getItemId();

        if (id == R.id.inicio) {
            Intent i=new Intent(vehicularActivity.this,principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            //Datos vehiculo
            i.putExtra("placas",placas)
                    .putExtra("polizaS",  polizaS)
                    .putExtra("Cfisico",Cfisico)
                    .putExtra("Cconta",Cconta)
                    .putExtra("nPoliza ",nPoliza )
                    .putExtra("campa", campa)
                    .putExtra("exp",exp )
                    .putExtra("venc",venc)
                    .putExtra("tarCir",tarCir)
            .putExtra("pref_via",pref_via);

            //Datos Conductor
            i.putExtra("licenciaO", licenciaO)
                    .putExtra("aptitud",aptitud)
                    .putExtra("bitacora", bitacora)
                    .putExtra("tl", tl)
                    .putExtra("expedlic", expedlic)
                    .putExtra("venclic", venclic)
                    .putExtra("num_licencia", num_licencia)
                    .putExtra("e", e);
            //vehicular
                    i.putExtra("ejesli",ejesli);
                    i.putExtra( "tipo_vehi",tipo_vehi);
            i.putExtra("ejesp",ejesp);
            i.putExtra("id_supervisor",id_supervisor);
                    i .putExtra("es",es);
                    i .putExtra("r",r);
                    startActivity(i);

            finish();

        } else if (id == R.id.documentacion) {
            Intent d=new Intent(vehicularActivity.this,DocActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            //Datos vehiculo
            d.putExtra("placas",placas)
                    .putExtra("polizaS",  polizaS)
                    .putExtra("Cfisico",Cfisico)
                    .putExtra("Cconta",Cconta)
                    .putExtra("nPoliza ",nPoliza )
                    .putExtra("campa", campa)
                    .putExtra("exp",exp )
                    .putExtra("venc",venc)
                    .putExtra("tarCir",tarCir);

            //Datos Conductor
            d.putExtra("licenciaO", licenciaO)
                    .putExtra("aptitud",aptitud)
                    .putExtra("bitacora", bitacora)
                    .putExtra("tl", tl)
                    .putExtra("expedlic", expedlic)
                    .putExtra("venclic", venclic)
                    .putExtra("num_licencia", num_licencia)
                    .putExtra("e", e);
            //vehicular
            d.putExtra("ejesli",ejesli)
            .putExtra( "tipo_vehi",tipo_vehi)
            .putExtra("ejesp",ejesp)

             .putExtra("es",es)
             .putExtra("r",r);

            startActivity(d);
            finish();
        } else if (id == R.id.carga) {
            Intent c=new Intent(vehicularActivity.this,cargaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            //Datos vehiculo
            c.putExtra("placas",placas)
                    .putExtra("polizaS",  polizaS)
                    .putExtra("Cfisico",Cfisico)
                    .putExtra("Cconta",Cconta)
                    .putExtra("nPoliza ",nPoliza )
                    .putExtra("campa", campa)
                    .putExtra("exp",exp )
                    .putExtra("venc",venc)
                    .putExtra("tarCir",tarCir);

            //Datos Conductor
            c.putExtra("licenciaO", licenciaO)
                    .putExtra("aptitud",aptitud)
                    .putExtra("bitacora", bitacora)
                    .putExtra("tl", tl)
                    .putExtra("expedlic", expedlic)
                    .putExtra("venclic", venclic)
                    .putExtra("num_licencia", num_licencia)
                    .putExtra("e", e);

            //vehicular
            c.putExtra("ejesli",ejesli)
                    .putExtra( "tipo_vehi",tipo_vehi)
                    .putExtra("ejesp",ejesp)

                    .putExtra("es",es)
                    .putExtra("r",r);
            startActivity(c);
            finish();
        } else if (id == R.id.viaticos) {
            Intent via=new Intent(vehicularActivity.this,viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(via);
            finish();
        } else if (id == R.id.salir) {
            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
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

            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehicularActivity.this);
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
                                    Intent cerrar = new Intent(vehicularActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(cerrar);
                                    finish();
                                }
                            });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();

        }else if (id == R.id.localizador) {
            Intent c=new Intent(vehicularActivity.this, LocalizacionActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(c);
            finish();

        } else if (id == R.id.configuracin) {

        } else if (id == R.id.uso) {

        }else if (id == R.id.condiciones) {

        } else if (id == R.id.soporte) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
