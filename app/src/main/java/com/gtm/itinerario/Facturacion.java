package com.gtm.itinerario;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class Facturacion extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView  Titulo, rqr;

    Spinner tipoG;
    Button btnqr,btnguardar,btncancelar;
    RelativeLayout rT,rQ;
    RadioButton T,Q;
    RadioGroup radioGroup;
    EditText edtFecha, num_factura,concepto;

    //fecha
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private static final int DATE_DIALOG_ID = 1;
    private int year;
    private int month;
    private int day;
    private Context context;
    private String currentDate;

    //
    static int TAKE_PIC =1;
    ImageView iv;
    LinearLayout qrr;
    TextView rfc, s,rfcEmp,Folio;
    static String tipogasto="",num_factura1="",conceptos1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion);
        //btnguardar.setVisibility(View.INVISIBLE);
        //TextView
        // tgasto=(TextView)findViewById(R.id.textView23);
        Titulo=(TextView)findViewById(R.id.textView4);
        rqr=(TextView) findViewById(R.id.textView6);
        rfc=(TextView)findViewById(R.id.rfcc);

        s=(TextView)findViewById(R.id.textView7);

        rfcEmp=(TextView)findViewById(R.id.textView11);
        //Spinner
        tipoG=(Spinner)findViewById(R.id.sptipo);
        tipoG.setVisibility(View.INVISIBLE);

        //Imagen

        iv=(ImageView)findViewById(R.id.imageView4);
        //RadioButton
        T=(RadioButton)findViewById(R.id.rdbt);
        Q=(RadioButton)findViewById(R.id.rdbq);
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);



        //Button

        btnqr=(Button)findViewById(R.id.button21);
        btnguardar=(Button)findViewById(R.id.bguardar);
        btncancelar=(Button)findViewById(R.id.bcancelar);

        //RelativeLayout
        rT=(RelativeLayout)findViewById(R.id.tiket);
        rQ=(RelativeLayout)findViewById(R.id.qr);

        //No visible los Relativeyout
        rT.setVisibility(View.GONE);
        rQ.setVisibility(View.GONE);

num_factura=(EditText)findViewById(R.id.editText4);
        concepto=(EditText)findViewById(R.id.editText5);

        // tgasto.setVisibility(View.VISIBLE);
        //Boton rq
        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Elija una opcion",Toast.LENGTH_SHORT).show();
            }
        });

        //Boton cancelar
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(Facturacion.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(g);
                finish();
            }
        });
//Fecha
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
        edtFecha.setOnClickListener(listenerDate);

        //Ticket
        final Activity activity = this;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rdbt) {
                    rT.setVisibility(View.VISIBLE);
                    tipoG.setVisibility(View.VISIBLE);
                    rQ.setVisibility(View.INVISIBLE);
                    btnqr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, TAKE_PIC);

                            rQ.setVisibility(View.INVISIBLE);
                            tipoG.setVisibility(View.INVISIBLE);
                        }
                    });
                }else  {
                    rT.setVisibility(View.INVISIBLE);
                    rQ.setVisibility(View.INVISIBLE);
                    tipoG.setVisibility(View.INVISIBLE);
                    iv.setImageDrawable(null);
                }
                if(checkedId == R.id.rdbq){

                    //Boton QR

                    btnqr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            IntentIntegrator integrator = new IntentIntegrator(activity);
                            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                            integrator.setPrompt("Escaneando..");
                            integrator.setCameraId(0);
                            integrator.setBeepEnabled(false);
                            integrator.setBarcodeImageEnabled(false);
                            integrator.initiateScan();
                        }
                    });
                }else{
                    rQ.setVisibility(View.INVISIBLE);
                    rT.setVisibility(View.INVISIBLE);
                    tipoG.setVisibility(View.INVISIBLE);
                    rqr.setText("");
                    rfc.setText("");
                    rfcEmp.setText("");
                    s.setText("");
                }
            }

        });


        List<String> categories = new ArrayList<String>();
        categories.add("TIPO DE GASTO");
        categories.add("Alimentos");
        categories.add("Transporte");
        categories.add("Gasolina");
        categories.add("Hospedaje");
        categories.add("Casetas");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        tipoG.setAdapter(dataAdapter);


    }
    //Fecha
    private void Iniz() {
        edtFecha = (EditText) findViewById(R.id.etdate);
        //No mo
        edtFecha.setInputType(InputType.TYPE_NULL);
    }

    private void updateDisplay() {
        currentDate = new StringBuilder().append(year).append("-")
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
            edtFecha.setText(currentDate);
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
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

    //QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
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
       final String pref_viaticos="1";

        //Datos vehicular
        final String re = getIntent().getStringExtra("r");
        final String es =getIntent().getStringExtra("es");

        final String ejesli = getIntent().getStringExtra ("ejesli");
        final String tipo_vehi= getIntent().getStringExtra("tipo_vehi");
        final String ejesp= getIntent().getStringExtra("ejesp");

        final String a = getIntent().getStringExtra ("a");
        final String fr = getIntent().getStringExtra("f");
        final String h = getIntent().getStringExtra("h");
        final String an = getIntent().getStringExtra("an");
        final String l = getIntent().getStringExtra("l");

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


        final String pref_via =getIntent().getStringExtra("pref_via");
//*************************************************
        rQ.setVisibility(View.VISIBLE);
//*************************************************
        tipoG.setVisibility(View. VISIBLE);
        btnguardar.setVisibility(View. VISIBLE);
        if(result != null) if (result.getContents() == null) {
            Log.d("MainActivity", "Escaner cancelado");
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
           rQ.setVisibility(View.INVISIBLE);
            tipoG.setVisibility(View.INVISIBLE);

        } else {
            Log.d("MainActivity", "Escanear");
            //Resultado
            String resultados = String.valueOf(result.getContents());

            Titulo.setText(resultados);

            boolean empiezaCon=resultados.substring(0).startsWith("?");

            if (empiezaCon){


                //RFC Empresa Factura
                int iniciorfc = resultados.indexOf("r=");
                int finrfc = resultados.indexOf("&tt=", iniciorfc + 2);

                rfc.setText(resultados.substring(iniciorfc + 2,finrfc));

              //RFC Empresa Factura
                int inicioEm = resultados.indexOf("re=");
                int finEm = resultados.indexOf("&rr=", inicioEm + 3);
                rfcEmp.setText( resultados.substring(inicioEm + 3, finEm));


                //Monto
                int i = resultados.indexOf("t=");
                int f = resultados.indexOf("&id=", i + 2);
                String r = resultados.substring(i + 2, f);


                boolean empiezaConCEro=r.substring(0).startsWith("0");

                if (empiezaConCEro){
                    int inicio = r.indexOf("=0");
                    int fin = r.indexOf(".", inicio + 1);
                    String resultado = r.substring(inicio + 1, fin);
                    //Quitar ceros a la izquierda
                    String cadenaResultadoString = resultado.replaceFirst("^0*", "");

                    int inicio1 = r.indexOf(".");
                    int fin1 = r.indexOf("00", inicio1 + 1);
                    String resultado1 = r.substring(inicio1 + 1, fin1);

                    rqr.setText(cadenaResultadoString + "." + resultado1 );

                }

                else{
                    int in = resultados.indexOf("t=");
                    int fi = resultados.indexOf(".", in + 2);
                    String resultado = resultados.substring(in + 2, fi);


                    int inicio1 = r.indexOf(".");
                    int fin1 = r.indexOf("00", inicio1 + 1);
                    String resultado1 = r.substring(inicio1 + 1, fin1);
                    rqr.setText( resultado + "." + resultado1 );

                }

                //Folio Fiscal
                String[] arreglo1 = cortarCadenaPorPuntos1(resultados);
                for (String s1 : arreglo1)
                    s.setText(s1);

                tipoG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final String item = parent.getItemAtPosition(position).toString();
                      final  String rfc1= edtFecha.getText().toString();
                       final String  rfcEmp1= rfcEmp.getText().toString();
                        final String rqr1=concepto.getText().toString();
                        final String s1= s.getText().toString();

    btnguardar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(item.equals("TIPO DE GASTO")){
            Toast.makeText(context,"Ingresar datos",Toast.LENGTH_LONG).show();
        }
        if (item.equals("Alimentos")){
            Toast.makeText(context,"Informacion guardada",Toast.LENGTH_LONG).show();

            final Synchronization sincronizar;
            sincronizar = new Synchronization(Facturacion.this);
            new Thread() {
                public void run() {
                    String msg = "";

                    try {

                        //Insertar en la tabla Factur ***************************************************************************************
                        String query1 = "INSERT INTO Factura1(id_conductor,tipo_facturacion,fecha_facturacion ,fehcahr_registro ,num_facturacion,concepto,RFC_emp ,RFC_emp_fact,monto,folio_fiscal   )" +
                                "values (16,'Alimentos','"+ rfc1 +"',GETDATE(),'"+num_factura.getText().toString()+"','"+concepto.getText().toString()+"','"+rfc.getText().toString()+"','"+rfcEmp1+"','"+rqr.getText().toString()+"' ,'"+s1+"' );";

                        sincronizar.saveInQueue(query1);//

                        int statusConnection = sincronizar.getStatusConnection();
                        if ((statusConnection == Operation.CONNECTION_STATUS_WIFI_OK) || (statusConnection == Operation.CONNECTION_STATUS_MOBILE_OK)) {
                            sincronizar.ApplyChanges();
                        }

                        Intent g = new Intent(Facturacion.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        g.putExtra("placas", placas)
                                .putExtra("pref_viaticos", pref_viaticos)
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
                                .putExtra("r", re)
                                .putExtra("ec", ec)
                                .putExtra("ecr", ecr)
                                .putExtra("ea", ea)
                                .putExtra("ein", ein)
                                .putExtra("ed", ed)
                                .putExtra("er", er)

                                .putExtra("a", a)

                                .putExtra("f", fr)
                                .putExtra("h", h)
                                .putExtra("id_supervisor", id_supervisor)
                                .putExtra("an", an)
                                .putExtra("l", l);
                        startActivity(g);

                    } catch (Exception e) {
                        Log.e("tag", e.getMessage());

                    }
                }
            }.start();

        }else
            //Transporte
            if(item.equals("Transporte")){
                Toast.makeText(context,"Informacion guardada",Toast.LENGTH_LONG).show();

                final Synchronization sincronizar;
                sincronizar = new Synchronization(Facturacion.this);
                new Thread() {
                    public void run() {
                        String msg = "";

                        try {

                            //Insertar en la tabla Factur ***************************************************************************************
                            String query1 = "INSERT INTO Factura1(id_conductor,tipo_facturacion,fecha_facturacion ," +
                                    "fehcahr_registro ,num_facturacion,concepto,RFC_emp ,RFC_emp_fact,monto,folio_fiscal   )" +
                                    "values (16,'Transporte','"+ rfc1 +"',GETDATE(),'"+num_factura.getText().toString()+"'," +
                                    "'"+concepto.getText().toString()+"','"+rfc.getText().toString()+"','"+rfcEmp1+"','"+rqr.getText().toString()+"' ,'"+s1+"' );";

                            sincronizar.saveInQueue(query1);//

                            int statusConnection = sincronizar.getStatusConnection();
                            if ((statusConnection == Operation.CONNECTION_STATUS_WIFI_OK) || (statusConnection == Operation.CONNECTION_STATUS_MOBILE_OK)) {
                                sincronizar.ApplyChanges();
                            }

                            Intent g = new Intent(Facturacion.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            g.putExtra("placas", placas)
                                    .putExtra("pref_viaticos", pref_viaticos)
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
                                    .putExtra("r", re)
                                    .putExtra("ec", ec)
                                    .putExtra("ecr", ecr)
                                    .putExtra("ea", ea)
                                    .putExtra("ein", ein)
                                    .putExtra("ed", ed)
                                    .putExtra("er", er)

                                    .putExtra("a", a)

                                    .putExtra("f", fr)
                                    .putExtra("h", h)
                                    .putExtra("id_supervisor", id_supervisor)
                                    .putExtra("an", an)
                                    .putExtra("l", l);
                            startActivity(g);

                        } catch (Exception e) {
                            Log.e("tag", e.getMessage());

                        }
                    }
                }.start();

            }else


                    //Hospedaje
                    if(item.equals("Hospedaje")){
                        Toast.makeText(context,"Informacion guardada",Toast.LENGTH_LONG).show();

                        final Synchronization sincronizar;
                        sincronizar = new Synchronization(Facturacion.this);
                        new Thread() {
                            public void run() {
                                String msg = "";

                                try {

                                    //Insertar en la tabla Factur ***************************************************************************************
                                    String query1 = "INSERT INTO Factura1(id_conductor,tipo_facturacion,fecha_facturacion," +
                                            "fehcahr_registro ,num_facturacion,concepto,RFC_emp ,RFC_emp_fact,monto,folio_fiscal   )" +
                                            "values (16,'Hospedaje','"+ rfc1 +"',GETDATE(),'"+num_factura.getText().toString()+"'," +
                                            "'"+concepto.getText().toString()+"','"+rfc.getText().toString()+"','"+rfcEmp1+"','"+rqr.getText().toString()+"' ,'"+s1+"' );";

                                    sincronizar.saveInQueue(query1);//

                                    int statusConnection = sincronizar.getStatusConnection();
                                    if ((statusConnection == Operation.CONNECTION_STATUS_WIFI_OK) || (statusConnection == Operation.CONNECTION_STATUS_MOBILE_OK)) {
                                        sincronizar.ApplyChanges();
                                    }

                                    Intent g = new Intent(Facturacion.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                                    g.putExtra("placas", placas)
                                            .putExtra("pref_viaticos", pref_viaticos)
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
                                            .putExtra("r", re)
                                            .putExtra("ec", ec)
                                            .putExtra("ecr", ecr)
                                            .putExtra("ea", ea)
                                            .putExtra("ein", ein)
                                            .putExtra("ed", ed)
                                            .putExtra("er", er)

                                            .putExtra("a", a)

                                            .putExtra("f", fr)
                                            .putExtra("h", h)
                                            .putExtra("id_supervisor", id_supervisor)
                                            .putExtra("an", an)
                                            .putExtra("l", l);
                                    startActivity(g);

                                } catch (Exception e) {
                                    Log.e("tag", e.getMessage());

                                }
                            }
                        }.start();

                    }else


                        //Casetas
                        if(item.equals("Casetas")){
                            //Boton guardar
                            btnguardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context,"QR no valido",Toast.LENGTH_LONG).show();
                                }});
                        }
       else

            //Gasolina
            if(item.equals("Gasolina")){
                Toast.makeText(context,"Informacion guardada",Toast.LENGTH_LONG).show();

                final Synchronization sincronizar;
                sincronizar = new Synchronization(Facturacion.this);
                new Thread() {
                    public void run() {
                        String msg = "";

                        try {

                            //Insertar en la tabla Factur ***************************************************************************************
                            String query1 = "INSERT INTO Factura1(id_conductor,tipo_facturacion,fecha_facturacion," +
                                    "fehcahr_registro ,num_facturacion,concepto,RFC_emp ,RFC_emp_fact,monto,folio_fiscal   )" +
                                    "values (16,'Gasolina','"+ rfc1 +"',GETDATE(),'"+num_factura.getText().toString()+"'" +
                                    ",'"+concepto.getText().toString()+"','"+rfc.getText().toString()+"','"+rfcEmp1+"','"+rqr.getText().toString()+"' ,'"+s1+"' );";

                            sincronizar.saveInQueue(query1);//

                            int statusConnection = sincronizar.getStatusConnection();
                            if ((statusConnection == Operation.CONNECTION_STATUS_WIFI_OK) || (statusConnection == Operation.CONNECTION_STATUS_MOBILE_OK)) {
                                sincronizar.ApplyChanges();
                            }

                            Intent g = new Intent(Facturacion.this, viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            g.putExtra("placas", placas)
                                    .putExtra("pref_viaticos", pref_viaticos)
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
                                    .putExtra("r", re)
                                    .putExtra("ec", ec)
                                    .putExtra("ecr", ecr)
                                    .putExtra("ea", ea)
                                    .putExtra("ein", ein)
                                    .putExtra("ed", ed)
                                    .putExtra("er", er)

                                    .putExtra("a", a)

                                    .putExtra("f", fr)
                                    .putExtra("h", h)
                                    .putExtra("id_supervisor", id_supervisor)
                                    .putExtra("an", an)
                                    .putExtra("l", l);
                            startActivity(g);

                        } catch (Exception e) {
                            Log.e("tag", e.getMessage());

                        }
                    }
                }.start();


            }else


                    //Casetas
                    if(item=="Casetas"){
                        //Boton guardar
                        btnguardar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context,"QR no valido",Toast.LENGTH_LONG).show();
                            }});
                    }

    }
});

  }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }


            else{
                rfc.setText("");
                rfcEmp.setText("");
                rqr.setText("");


                //s.setText("Folio Fiscal= " + resultados);

                s.setText(resultados);

                tipoG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Alimentos
                        if (position==1){
                            //Boton guardar
                            btnguardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context, "QR no valido", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else

                            //Transporte
                            if(position==2){
                                //Boton guardar
                                btnguardar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(context,"QR no valido",Toast.LENGTH_LONG).show();

                                    }
                                });
                            }else

                                //Gasolina
                                if(position==3){
                                    //Boton guardar
                                    btnguardar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(context,"QR no valido",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }else

                                    //Hospedaje
                                    if(position==4){
                                        //Boton guardar
                                        btnguardar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(context,"QR no valido",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }else


                                        //Casetas
                                        if(position==5){
                                            //Boton guardar
                                            btnguardar.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent g = new Intent(Facturacion.this,
                                                            viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                    startActivity(g);

                                                    Toast.makeText(context,"Informacion guardada",Toast.LENGTH_LONG).show();
                                                    finish();
                                                }});
                                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(bp);


        }
    }

    public String[] cortarCadenaPorPuntos1(String cadena) {
        return cadena.split("id=");
    }


    @Override
    public void onBackPressed() {
    }
}
