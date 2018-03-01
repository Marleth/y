

package com.gtm.itinerario;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseSync.Synchronization;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DocActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static   String tarCir="1",polizaS="1",Cconta="1",Cfisico="1";
    static String bitacora = "1", aptitud = "1", licenciaO = "1";
    Button  enviar_inf,cancelar;
    Switch tc,poliza,fisico,conta;
    EditText obser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String doc_editar= getIntent().getStringExtra("doc_editar");
        final String  placas = getIntent().getStringExtra( "placas");
        final String  id_supervisor = getIntent().getStringExtra( "id_supervisor");

        obser=(EditText)findViewById(R.id.editText3);
        enviar_inf=(Button)findViewById(R.id.button16);
        cancelar=(Button)findViewById(R.id.button );
        tc=(Switch)findViewById(R.id.switch_tarjeta);
        poliza=(Switch)findViewById(R.id.switch2);
        fisico=(Switch)findViewById(R.id.switch1);
        conta=(Switch)findViewById(R.id.switch3);
          tc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if (isChecked) {
                    tarCir="0";
                } else {
                    tarCir="1";
                }
            }
        });
        poliza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    polizaS="0";
                } else {
                    polizaS="1";
                }
            }
        });
        conta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if (isChecked) {
                    Cconta="0";
                } else {
                    Cconta="1";
                }
            }
        });


        //Fisico
        fisico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Cfisico="0";
                } else {
                    Cfisico="1";
                }
            }
        });
        final String pref_via="2";
        enviar_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String observaciones=obser.getText().toString();

                Intent docu= new Intent(DocActivity.this, principalActivity.class);
                docu.putExtra("pref_via",pref_via)
                .putExtra("tarCir",tarCir)
                .putExtra("polizaS",polizaS)
                        .putExtra("observaciones",observaciones)
                .putExtra("Cconta",Cconta)
                        .putExtra("placas",placas)
                        .putExtra("id_supervisor",id_supervisor)
                        .putExtra("Cfisico",Cfisico);
                startActivity(docu);
                Toast.makeText(DocActivity.this, "Envio exitoso", Toast.LENGTH_SHORT).show();

            }

        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent docu= new Intent(DocActivity.this, principalActivity.class);
                startActivity(docu);
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
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doc, menu);
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
        final String pref_via=getIntent().getStringExtra("pref_via");
        final String id_supervisor=getIntent().getStringExtra("id_supervisor");
        int id = item.getItemId();

        if (id == R.id.inicio) {
            Intent i=new Intent(DocActivity.this,principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            i.putExtra("pref_via",pref_via);
            i.putExtra("id_supervisor",id_supervisor);
            startActivity(i);
            finish();
        }  else if (id == R.id.vehicular) {
            Intent v=new Intent(DocActivity.this,vehicularActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(v);
            finish();
        } else if (id == R.id.carga) {
            Intent c=new Intent(DocActivity.this,cargaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(c);
            finish();
        } else if (id == R.id.viaticos) {
            Intent via=new Intent(DocActivity.this,viaticosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);



            startActivity(via);
            finish();
        }
        else if (id == R.id.localizador) {
            Intent c=new Intent(DocActivity.this, LocalizacionActivity.class)
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
