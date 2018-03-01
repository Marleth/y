package com.gtm.itinerario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class StreetView extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        // Getting reference to rb_driving
        StreetViewPanoramaFragment streetViewPanoramaFragment=(StreetViewPanoramaFragment)
                getFragmentManager().findFragmentById(R.id.map1);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync((OnStreetViewPanoramaReadyCallback)this);
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        Bundle b2 = new Bundle();
        b2=getIntent().getExtras();
        double Latitud=b2.getDouble("latitudMarker");
        double longitud=b2.getDouble("longitudMarker");
        LatLng newark= new LatLng(Latitud,longitud);

        if(panorama==null){

            panorama.setPosition(newark);
        }else {
            Intent c=new Intent(StreetView.this,LocalizacionActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity(c);
            Toast s=Toast.makeText(getApplicationContext(),"Seleccione otro lugar",Toast.LENGTH_LONG);
            s.show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent c= new Intent(StreetView.this,LocalizacionActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP );
        startActivity(c);

    }


}