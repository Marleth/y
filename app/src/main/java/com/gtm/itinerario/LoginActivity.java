package com.gtm.itinerario;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

//Importamos para sharedPreferences
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    ImageButton entrar,salir;
    Button x;
    static String id_supervisor="",id_conductor="";
    static int TAKE_PIC =1;
    private ProgressDialog progressDialog;

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progressDialog.dismiss();
            try {

                Intent i = new Intent(LoginActivity.this, principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            } catch (Exception e) {
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
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
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
        setContentView(R.layout.activity_login);
        entrar=(ImageButton)findViewById(R.id.imageButton11);
        salir=(ImageButton)findViewById(R.id.imageButton12);



        SharedPreferences pref = this.getSharedPreferences("Mipreferencia", Context.MODE_PRIVATE);
        String user = pref.getString("user", "");
       //pref.edit().clear().commit();

        if (!user.equals("") ) {

            Intent i = new Intent(LoginActivity.this, principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
            finish();
            // pref.edit().clear().commit();
        }
        final Activity activity = this;
           entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);

  /*Inicio                 IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Escaneando..");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
 Fin*/
            }
        });

        salir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Deseas salir")
                        .setTitle("Advertencia")
                        .setIcon(getResources().getDrawable(R.drawable.ic_advertencia))
                        //.setInverseBackgroundForced(getResources().getColor())

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
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final Synchronization sincronizar;
        sincronizar = new Synchronization(this);
        String curp;
        final String curp_super="" ;

        if(result != null)
            if (result.getContents() == null) {
            Log.d("MainActivity", "Escaner cancelado");
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        } else {
            final String resultados = String.valueOf(result.getContents());

             progressDialog = ProgressDialog.show(LoginActivity.this, "", "Validando Usuario...");
            new Thread() {

                public void run() {
                    String msg = "";
                    try {
                       /* int connectionStatus = sincronizar.getStatusConnection();
                        if (   connectionStatus == Operation.CONNECTION_STATUS_WIFI_OK ||
                                connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                            sincronizar.Synchronize();
                        } else {
                            Log.e("tag", "Error de conexión");
                            Message message = handlerError.obtainMessage();
                            message.obj = "Error al intentar sincronizar.";
                            handlerError.sendMessage(message);
                        }
                        ArrayList<ArrayList<String>> id_super = sincronizar.ExecuteSelect("Select id_supervisor,curp_super from Supervisor WHERE curp_super = '" + resultados.toString() + "' ", null);

                        if (id_super.size() == 1) {

                            id_supervisor = id_super.get(0).get(0);
                            id_conductor=id_super.get(0).get(1);
                        }

                        id_super = null;
                        if (resultados.toString().equals(id_conductor)) {
                            handler.sendEmptyMessage(1);
                        } else {
                            Message message = handlerError.obtainMessage();
                            message.obj = "Usuario no autorizado";
                            handlerError.sendMessage(message);
                        }*/
                        handler.sendEmptyMessage(1);
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

    }}