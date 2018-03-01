package com.gtm.itinerario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

import java.io.File;
import java.util.ArrayList;

public class cargaActivity extends Activity {

    Synchronization sincronizar;
    private ProgressDialog progressDialog;
    Button car;
   /* final String idconductor = getIntent().getStringExtra("idconductor");
    final String idpedido=getIntent().getStringExtra("idpedido");
    static String id_pedido="",num_factura="", id_cliente ="",id_producto="",cantidad_pro="";
*/
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progressDialog.dismiss();
            try {
                Intent servicio = new Intent(cargaActivity.this, Servicio.class);
                if (servicio != null) {
                    startService(servicio);
                }
            } catch (Exception e) {
            }
            /*String x = idconductor;
            startActivity(new Intent(getBaseContext(), principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    .putExtra("idconductor",idconductor)
                    .putExtra("contraseña", contraseña));
            */finish();
        }
    };

    @SuppressLint("HandlerLeak")
    public Handler handlerError = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String message = null;
            try {
                message = (String)msg.obj;
            } catch (Exception ex) {
                message = "Usuario no autorizado";
            } finally {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(cargaActivity.this);
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);
        final String pref_via ="4";
        final String id_conductor = getIntent().getStringExtra( "id_conductor");
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

        final String observaciones= getIntent().getStringExtra( "observaciones");

        final String licenciaO= getIntent().getStringExtra("licenciaO");
        final String num_licencia = getIntent().getStringExtra("num_licencia");
        final String  aptitud = getIntent().getStringExtra("aptitud");
        final String  bitacora= getIntent().getStringExtra("bitacora");
        final String tl= getIntent().getStringExtra("tl");
        final String ve= getIntent().getStringExtra("ve");
        final String e=getIntent().getStringExtra("e");
        final String curp_conducto=getIntent().getStringExtra("curp_conducto");
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

        final String po4="4";
        car=(Button) findViewById(R.id.btncargar);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(cargaActivity.this,principalActivity.class);
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
                        .putExtra("id_conductor",id_conductor)
                        .putExtra("observaciones",observaciones)
                        .putExtra("id_supervisor",id_supervisor);

                //Datos Conductor
                i.putExtra("licenciaO", licenciaO)
                        .putExtra("aptitud",aptitud)
                        .putExtra("bitacora", bitacora)
                        .putExtra("tl", tl)
                        .putExtra("ve", ve)
                        .putExtra("num_licencia",num_licencia)
                        .putExtra("e", e);
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
                        .putExtra("l",l)
                        .putExtra("curp_conducto",curp_conducto)
                .putExtra("po4",po4);
                i.putExtra("pref_via",pref_via);
                startActivity(i);



              /*  try {
                    int connectionStatus = sincronizar.getStatusConnection();
                    if (connectionStatus == Operation.CONNECTION_STATUS_BOOTH_OK || connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK || connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                        sincronizar.Synchronize();
                    } else {
                        Log.e("tag", "Error de conexión");
                        Message message = handlerError.obtainMessage();
                        message.obj = "Error al intentar sincronizar.";
                        handlerError.sendMessage(message);
                    }
                    ArrayList<ArrayList<String>> pedidos = sincronizar.ExecuteSelect("Select id_pedido,num_factura, id_cliente,id_producto,cantidad_pro from Pedido WHERE id_pedido= '"+idpedido+"'", null);
                    if (pedidos != null) {
                        id_pedido=pedidos.get(0).get(0);
                        num_factura=pedidos.get(0).get(1);
                        id_cliente =pedidos.get(0).get(2);
                        id_producto=pedidos.get(0).get(3);
                        cantidad_pro=pedidos.get(0).get(4);

                   /*Mostrar el registro de la tabla
                    }

                } catch (Exception e) {
                    // Log.e("tag", e.getMessage());
                    Message message = handlerError.obtainMessage();
                    message.obj = "Error al obtener datos del usuario.";
                    handlerError.sendMessage(message);
                }*/
            }
        });



    }

    @Override
    public void onBackPressed() {

    }
}