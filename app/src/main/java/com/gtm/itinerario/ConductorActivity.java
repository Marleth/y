package com.gtm.itinerario;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.DatabaseSync.Synchronization;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ConductorActivity extends AppCompatActivity {
    public TextView conductor, expedicion, vencimiento, num, tipo;
    public Spinner tipolicencia;
    Synchronization sincronizar;
    private ProgressDialog progressDialog;

    public EditText ed1;
    public Button guardar, cancelar;
    Switch licencia, bita, ampli;
    RelativeLayout licen;
    TextView d26;
    EditText fromDateEtxt, toDateEtxt ;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String bitacora = "", aptitud = "", licenciaO = "";
    static String num_licencia = "", expedlic= "", venclic = "", tl = "",e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);



    }


    @Override
    public void onBackPressed() {

    }


}
