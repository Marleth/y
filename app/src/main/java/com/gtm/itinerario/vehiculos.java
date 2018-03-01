package com.gtm.itinerario;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class vehiculos extends AppCompatActivity {
    TextView  num,compa, vehi;
    EditText npoli,compañia,expedicion,vencimiento,edplacas;
    Switch poliza,contaminantes,fisico,cir;
    RelativeLayout lapoliza,circulacion;
    Button guardar,cancelar;
    Synchronization sincronizar;
    private ProgressDialog progressDialog;


    private String tarCir="",polizaS=" ",Cconta="",Cfisico="",nPoliza="" ,campa="" ,exp="",venc="",placas="";


//DATEPICKER
     TextView fromDateEtxt,toDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Datos vehiculos
        final String  placasv = getIntent().getStringExtra( "placas");
        final String polizaSv= getIntent().getStringExtra( "polizaS");
        final String Cfisicov= getIntent().getStringExtra( "Cfisico");
        final String Ccontav= getIntent().getStringExtra( "Cconta");
        final String  nPolizav= getIntent().getStringExtra( "nPoliza ");
        final String campav= getIntent().getStringExtra( "campa");
        final String   expv= getIntent().getStringExtra( "exp");
        final String  vencv= getIntent().getStringExtra( "venc");
        final String tarCirv= getIntent().getStringExtra( "tarCi");

        //Datos Conductor
        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String expedlic = getIntent().getStringExtra("expedlic");
        final String venclic = getIntent().getStringExtra("venclic");
        final String e=getIntent().getStringExtra("e");

          num=(TextView)findViewById(R.id.textView20);
        compa=(TextView)findViewById(R.id.textView21);
        vehi=(TextView)findViewById(R.id.textView18);
   npoli=(EditText)findViewById(R.id.editText9);
        compañia=(EditText)findViewById(R.id.editText10);
        edplacas=(EditText)findViewById(R.id.edplacas);

        expedicion =(EditText)findViewById(R.id.exp);
        vencimiento=(EditText)findViewById(R.id.ven);

        poliza=(Switch)findViewById(R.id.switch11);
        contaminantes=(Switch)findViewById(R.id.switch12);
        fisico=(Switch)findViewById(R.id.switch13);

        //Switch
        lapoliza=(RelativeLayout)findViewById(R.id.poli);
        lapoliza.setVisibility(View.GONE);

       poliza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    lapoliza.setVisibility(View.VISIBLE);
                    polizaS="1";


                } else {
                    lapoliza.setVisibility(View.GONE);
                    polizaS="0";

                }
            }
        });

        contaminantes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Cconta="1";
                } else {
                    Cconta="0";
                }
            }
        });


        //Fisico
        fisico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    Cfisico="1";

                } else {
                    Cfisico="0";
                }
            }
        });


        cir=(Switch)findViewById(R.id.circu);
        circulacion=(RelativeLayout)findViewById(R.id.circula);
        circulacion.setVisibility(View.GONE);

       cir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    circulacion.setVisibility(View.VISIBLE);
                    tarCir="1";

                } else {
                   circulacion.setVisibility(View.GONE);
                    tarCir="0";

                }
            }
        });


        //Boton guardar
        guardar=(Button)findViewById(R.id.button6);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        nPoliza=npoli.getText().toString();
        campa=compañia.getText().toString();
        exp=fromDateEtxt.getText().toString();
        venc=toDateEtxt.getText().toString();
        placas=edplacas.getText().toString();

      /*          final ProgressDialog progressDialog = ProgressDialog.show(vehiculos.this, "", "Guardando datos...");
                new Thread() {
                    public void run() {
                        String msg = "";
                        try {
                            int seguro=sincronizar.ExecuteNonQuery("Insert into Seguro(num_seguro,compañia,emi_poliza ," +
                                    "venci_poliza values ('"+campa+"','"+nPoliza+"','"+exp+"','"+venc+"');",Operation.QUERY_INSERT);
                         } catch (Exception e) {
                            Log.e("tag", e.getMessage());
                            msg = "-1";
                        }

                    }
                }.start();
                ArrayList<ArrayList<String>> seguro = sincronizar.ExecuteSelect("Select id_seguro from Seguro WHERE num_seguro= '"+ nPoliza+ "'", null);
                if (seguro != null) {
                    if (seguro.size() == 1) {
                        /*** Guarda los registros de la consulta en las variables *****
                        id_seguro=seguro.get(0).get(0);
                        // nomSuperv = usuarios.get(0).get(2) + " " + usuarios.get(0).get(3) + " " + usuarios.get(0).get(4);
                    }
                }*/
                Intent i = new Intent(getApplicationContext(), DocActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

               //Datos Vehiculo
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
                        .putExtra("num_licencia", num_licencia)
                        .putExtra("e", e);

                startActivity(i);
            }
        });

        //Boton Cancelar
        cancelar=(Button)findViewById(R.id.button7);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo para verificar si desea guardar
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(vehiculos.this);
                builder.setMessage("Deseas salir")
                        .setTitle("Advertencia")
                        .setIcon(getResources().getDrawable(R.drawable.ic_advertencia))
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                        .setPositiveButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent g = new Intent(vehiculos.this, DocActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        g.putExtra("placas",placasv)
                                                .putExtra("polizaS",  polizaSv)
                                                .putExtra("Cfisico",Cfisicov)
                                                .putExtra("Cconta",Ccontav)
                                                .putExtra("nPoliza ",nPolizav)
                                                .putExtra("campa",campav)
                                                .putExtra("exp",expv)
                                                .putExtra("venc",vencv)
                                                .putExtra("tarCir",tarCirv);
                                        startActivity(g);
                                        finish();
                                    }
                                });
                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });
        //DATEPICKER

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        findViewsById();

        setDateTimeField();
    }
    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.exp);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.ven);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }


    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
 }
        });


        toDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();

            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onBackPressed() {

    }

}
