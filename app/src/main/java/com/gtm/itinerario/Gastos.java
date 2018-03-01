package com.gtm.itinerario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Gastos extends AppCompatActivity   implements DatePickerDialog.OnDateSetListener{


    Spinner tipo;
    //Fecha
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private static final int DATE_DIALOG_ID = 1;
    private int year;
    private int month;
    private int day;
    EditText editTextDate;
    private String currentDate;
    private Context context;
 static String tgasto="",concep="",fecha="",m="",tipo_pago="",nTarje="";

    //Radiogroup
    RadioGroup rdG;
    TextView nT;
    Button bg,bc;
    EditText edT,concepto,monto;
    LinearLayout con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        con=(LinearLayout)findViewById(R.id.con);
        con.setVisibility(View.INVISIBLE);

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

        final String   id_vehiculo  = getIntent().getStringExtra ("id_vehiculo");
        final String ec=getIntent().getStringExtra("ec");
        final String ecr=getIntent().getStringExtra("ecr");
        final String ea=getIntent().getStringExtra("ea");
        final String ein=getIntent().getStringExtra("ein");
        final String ed=getIntent().getStringExtra("ed");
        final String er=getIntent().getStringExtra("er");
        final String id_supervisor=getIntent().getStringExtra("id_supervisor");
        final String curp_conducto=getIntent().getStringExtra("curp_conducto");
        final String observaciones= getIntent().getStringExtra( "observaciones");
        final String id_conductor = getIntent().getStringExtra( "id_conductor");
        final String pref_via =getIntent().getStringExtra("pref_via");

        tipo=(Spinner)findViewById(R.id.spinner2);
        concepto=(EditText)findViewById(R.id.editText7);


        List<String> categories = new ArrayList<String>();
        categories.add("TIPO DE GASTO ");
        categories.add("Alimentos");
        categories.add("Transporte");
        categories.add("Gasolina");
        categories.add("Hospedaje");
        categories.add("Caseta");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                              tgasto = parent.getItemAtPosition(position).toString();

                                               if(tgasto=="Alimentos"){
                                                   con.setVisibility(View.VISIBLE);
                                               }else
                                              if (tgasto=="Transporte") {
                                                   con.setVisibility(View.VISIBLE);
                                               }else
                                               if (tgasto=="Gasolina") {
                                                   con.setVisibility(View.VISIBLE);
                                               }else
                                               if (tgasto=="Hospedaje") {
                                                   con.setVisibility(View.VISIBLE);
                                               }else {
                                                   con.setVisibility(View.INVISIBLE);
                                               }

                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> parent) {

                                           }
                                       }
        );

        tipo.setAdapter(dataAdapter);

        //DATEPICKER
              Iniz();
        context = getApplicationContext();
        View.OnClickListener listenerDate = new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                showDialog(DATE_DIALOG_ID);
            }
        };
        editTextDate.setOnClickListener(listenerDate);



        //N° tarjeta y editText
        nT=(TextView)findViewById(R.id.textView3);
        nT.setVisibility(View.INVISIBLE);
        edT=(EditText)findViewById(R.id.editText);
        edT.setVisibility(View.INVISIBLE);

        //RadioGroup
        rdG=(RadioGroup)findViewById(R.id.mR);
        rdG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Efectivo
                if(checkedId == R.id.radioButton2) {
                    tipo_pago="Efectivo";
                    edT.setText(" ");
                }
                //Tarjeta
                if(checkedId==R.id.radioButton){
                    nT.setVisibility(View.VISIBLE);
                    edT.setVisibility(View.VISIBLE);
                    tipo_pago="Tarjeta";
                    edT.setText(" ");
                }else{
                    nT.setVisibility(View.INVISIBLE);
                    edT.setVisibility(View.INVISIBLE);
                }
                }
        });
        monto=(EditText)findViewById(R.id.mon);

        final String pref_viaticos="2";
        //bg
        bg=(Button)findViewById(R.id.guarda);


        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concep=concepto.getText().toString();
            fecha= editTextDate.getText().toString();
                m=monto.getText().toString();
                nTarje=edT.getText().toString();

                   final AlertDialog.Builder gasto_general = new AlertDialog.Builder(Gastos.this);
                   final View custom = View.inflate(Gastos.this, R.layout.content_general__gasto, null);
                   gasto_general.setView(custom);
                   TextView gGasto = (TextView) custom.findViewById(R.id.textView38);
                   Calendar c=Calendar.getInstance();
                   int seconds=c.get((Calendar.SECOND));

                   gGasto.setText(tgasto + "\n" + concep + "\n" + fecha + "\n" + pref_viaticos + "\n" + m + "\n" + tipo_pago + "\n" + nTarje);
                   final AlertDialog alertDialog = gasto_general.create();
                   Button guardar = (Button) custom.findViewById(R.id.bt2);
                   final Synchronization sincronizar;
                   sincronizar = new Synchronization(Gastos.this);
                   guardar.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {



                                                      new Thread() {
                                                          public void run() {
                                                              String msg = "";

                                                              try {

                                                                 // "," + fecha_actual+
                                                                  //Insertar en la tabla Seguro
                                                                  String query = "INSERT INTO Gasto(id_conductor,tipo_gasto, concepto,fecha_gasto,monto,tpago,num_tarjeta,fecha_hr_registro) " +
                                                                          "values ("+id_conductor+",'"+tgasto+"','"+ concep+"','"+fecha+"',"+m+",'"+tipo_pago+"','"+ nTarje +"',GETDATE());";
                                                                   sincronizar.saveInQueue(query);

                                                                  int statusConnection = sincronizar.getStatusConnection();
                                                                  if((statusConnection== Operation.CONNECTION_STATUS_WIFI_OK )|| (statusConnection==Operation.CONNECTION_STATUS_MOBILE_OK)){

                                                                      sincronizar.ApplyChanges();
                                                                  }

                                                                      Intent g = new Intent(Gastos.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                                      g.putExtra("tgasto", tgasto)
                                                                              .putExtra("concep", concep)
                                                                              .putExtra("fecha", fecha)
                                                                              .putExtra("pref_viaticos", pref_viaticos)
                                                                              .putExtra("m", m)
                                                                              .putExtra("tipo_pago", tipo_pago)
                                                                              .putExtra("nTarje", nTarje);

                                                                      g.putExtra("placas", placas)
                                                                              .putExtra("polizaS", polizaS)
                                                                              .putExtra("Cfisico", Cfisico)
                                                                              .putExtra("Cconta", Cconta)
                                                                              .putExtra("nPoliza ", nPoliza)
                                                                              .putExtra("campa", campa)
                                                                              .putExtra("exp", exp)
                                                                              .putExtra("venc", venc)
                                                                              .putExtra("tarCir", tarCir)
                                                                              .putExtra("curp_conducto", curp_conducto)
                                                                              .putExtra("observaciones", observaciones);

                                                                      //Datos Conductor
                                                                      g.putExtra("licenciaO", licenciaO)
                                                                              .putExtra("aptitud", aptitud)
                                                                              .putExtra("bitacora", bitacora)
                                                                              .putExtra("tl", tl)
                                                                              .putExtra("expedlic", expedlic)
                                                                              .putExtra("venclic", venclic)
                                                                              .putExtra("num_licencia", num_licencia)
                                                                              .putExtra("e", e);
                                                                      g.putExtra("pref_via", pref_via);

                                                                      //Datos Vehicular
                                                                      g.putExtra("ejesli", ejesli)
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
                                                                              .putExtra("id_supervisor", id_supervisor)
                                                                              .putExtra("id_vehiculo", id_vehiculo)
                                                                              .putExtra("id_conductor", id_conductor)
                                                                              .putExtra("an", an)
                                                                              .putExtra("l", l);
                                                                      startActivity(g);

                                                                   alertDialog.dismiss();
                                                              } catch (Exception e) {
                                                                  Log.e("tag", e.getMessage());

                                                              }

                                                          }
                                                      }.start();



                                                  }
                                              }
                   );

                   alertDialog.show();





                //Toast.makeText(context,"Datos guardados correctamente",Toast.LENGTH_SHORT).show();
                //finish();
            }
        });

        //bc
        bc=(Button)findViewById(R.id.button4);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Gastos.this,viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(c);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
    private void Iniz() {
        // TODO Auto-generated method stub

        editTextDate = (EditText) findViewById(R.id.date);
        //quitar el teclado
        editTextDate.setInputType(InputType.TYPE_NULL);

    }


    private void updateDisplay() {
        currentDate = new

                StringBuilder().append(year).append("-")
                .append(month + 1).append("-").append(day).toString();

        Log.i("DATE", currentDate);
    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int j, int k) {

            year = i;
            month = j;
            day = k;
            updateDisplay();
            editTextDate.setText(currentDate);
        }
    };



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, myDateSetListener, year, month,
                        day);
        }
        return null;
    }
    @Override
    public void onBackPressed() {
    }
}
