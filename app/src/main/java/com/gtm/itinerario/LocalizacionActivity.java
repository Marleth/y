package com.gtm.itinerario;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.drive.internal.StringListResponse;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.identity.intents.AddressConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalizacionActivity extends FragmentActivity implements
        GoogleMap.OnMapClickListener, OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerDragListener {


    private final LatLng UPV = new LatLng(39.481106, -0.340987);
    private GoogleMap mapa;

    ArrayList<LatLng> markerPoints;
    ArrayList<LatLng> markerstreet;
    Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);

        TextView tiempo = (TextView) findViewById(R.id.tiempo);
        tiempo.setVisibility(View.INVISIBLE);
        // Initializing
        markerPoints = new ArrayList<LatLng>();
        markerstreet = new ArrayList<LatLng>();
        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMapToolbarEnabled(false);


        mapa.setOnMapClickListener(this);
        markerstreet = new ArrayList<LatLng>();
        mapa.setOnMapLongClickListener(this);
        mapa.setOnMarkerClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mapa.setMyLocationEnabled(true);

    }

    @Override
    public void onMapClick(LatLng point) {
        //Cuando es un marcador se elimina la ruta y se limpia el mapa
        if (markerPoints.size() > 1) {
            markerPoints.clear();
            markerstreet.clear();
            mapa.clear();
        }
        //Adición de un nuevo elemento a la ArrayList
        markerPoints.add(point);
        drawStartStopMarkers();
        //cuando son dos marcadores
        if (markerPoints.size() >= 2) {
            LatLng origin = markerPoints.get(0);
            LatLng dest = markerPoints.get(1);

            LatLng origen = markerPoints.get(0);
            LatLng destino = markerPoints.get(1);
            // Obtención de URL al llegar API Google
            String url = getDirectionsUrl(origin, dest);
            DownloadTask downloadTask = new DownloadTask();
            tiempo(origen, destino);
            // Iniciar la descarga de datos JSON de Google Directions API
            downloadTask.execute(url);
        }

    }

    // Dibujo lugares de inicio y parada
    private void drawStartStopMarkers() {

        for (int i = 0; i < markerPoints.size(); i++) {
            TextView tiempo = (TextView) findViewById(R.id.tiempo);
            tiempo.setVisibility(View.INVISIBLE);
            // Creacion de  MarkerOptions
            MarkerOptions options = new MarkerOptions();

            // Ajuste de la posición del marcador
            options.position(markerPoints.get(i));

            /**
             *
             para la ubicación de inicio , el color del marcador es verde y
             * Para la ubicación final , el color del marcador es ROJO .
             */
            if (i == 0) {
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                // options.draggable(true);

            } else if (i == 1) {
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

            // Añadir un nuevo marcador para la versión 2 del API Google Map Android
            mapa.addMarker(options);
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
// Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Travelling Mode
        String mode = "mode=driving";


        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {



        // mStreetViewPanorama.setPosition(marker.getPosition(), 150);
    }


    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));

                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);

                // Changing the color polyline according to the mode

                lineOptions.color(Color.RED);

            }

            if (result.size() < 1) {
                Toast.makeText(getBaseContext(), "Sin puntos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Drawing polyline in the Google Map for the i-th route
            mapa.addPolyline(lineOptions);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

      /*  if(marker.equals(marker)) {
            Double latitud = mMarker.getPosition().latitude;
            Double longitud = mMarker.getPosition().longitude;
            Intent itemintent = new Intent(this, StreetView.class);
            itemintent.putExtra("latitud", latitud);
            itemintent.putExtra("longitud", longitud);
            startActivity(itemintent);
}*/
    return false;
    }



    @Override
    public void onMapLongClick(LatLng latLong) {
        TextView tiempo = (TextView) findViewById(R.id.tiempo);
        tiempo.setVisibility(View.INVISIBLE);

        Double latitud = latLong.latitude;
        Double longitud = latLong.longitude;

        if (markerstreet.size() ==0) {

           mMarker = mapa.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud, longitud))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pegmanq))
                    .draggable(true));

            Double latitudMarker=mMarker.getPosition().latitude;
            Double longitudMarker=mMarker.getPosition().longitude;

            Intent itemintent = new Intent(this, StreetView.class);
            itemintent.putExtra("latitudMarker",latitudMarker);
            itemintent.putExtra("longitudMarker", longitudMarker);
            startActivity(itemintent);

        }    markerstreet.add(latLong);


    }


    private double tiempo(LatLng origen, LatLng destino){
       //Distancia
        double Radio=  3958.75;//radius of earth in Km( 6,371)  mi

        //Longitud y Latitud de los dos puntos en el mapa
        double p1Lat=origen.latitude;
        double p1Long=origen.longitude;
        double p2Lat=destino.latitude;
        double p2Long=destino.longitude;

        // Formula Longitud y Latitud total
        double dlat=Math.toRadians(p2Lat - p1Lat);
        double dlon=Math.toRadians(p2Long - p1Long);

        //Formula para sacar distancia de los dos puntos
        double va1 = Math.sin(dlat / 2)*Math.sin(dlat/2)
                +Math.cos(Math.toRadians(p1Lat))
                *Math.cos(Math.toRadians(p2Lat))*Math.sin(dlon / 2)
                *Math.sin(dlon/2);
        double va2 = 2*Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));

        double distancia= Radio*va2; //Multiplica el radio de la tierra por va2

        int metroConversion=1609;
      //  double klconversion=1.60934;

        double dis=(distancia*metroConversion);
       // double dd=(distancia*klconversion);

        //Velocidad estatica
       double velocidad=80; //kmts/hr

        //Redondeo el resultado de distancia
        DecimalFormat metross=new DecimalFormat("0.0");
        String resuld=metross.format(dis);

        //Formula de tiempo t=d/v
        double t=dis/velocidad; //resultado en decimales --> hr

        //Formato para cuantos digitos tomar despues del punto
       // DecimalFormat metross=new DecimalFormat("0.0000");
        //String s=metross.format(distancia);

       //Resultado  de m solo un digito despues del punto
       // String result=metross.format(t);
        //String resuld=metross.format(distancia);

        //Convertir resultado decimal a formato de tiempo
     /* int  hora,min,seg,temp;
        hora= (int)t/24;
        temp=(int)t%24;
        min=temp/60;
        seg=temp%60;
        double x = hora;
*/

        //Visible el TextView para mostrar los resultados (tiempo y distancia)
        TextView tiempo= (TextView)findViewById(R.id.tiempo);
        tiempo.setVisibility(View.VISIBLE);

        //
        String resultados=String.valueOf( "\n"+ "Tiempo "+t +"\n"+
                        "Distancia "+resuld+"metros"+"\n");

tiempo.setText(resultados);

        return t;
    }
    @Override
    public void onBackPressed() {


        Intent i=new Intent(LocalizacionActivity.this,principalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(i);

    }
    }


