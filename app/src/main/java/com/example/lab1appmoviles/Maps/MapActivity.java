package com.example.lab1appmoviles.Maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.lab1appmoviles.PedidoActivity;
import com.example.lab1appmoviles.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap myMap;
    private Button botonubi;
    private LatLng latlong = null;
    private LatLng posicionGPS = null;
    private LatLng nuevaPosicionResto = null;
    private Boolean marcador = false;
    private FusedLocationProviderClient fusedLocationClient;
    private Address address = null;
    private String direccionGPS = null;
    private String direccionMarcador = null;
    private List<Address> addresses;
    private Geocoder geocoder;
    private Polyline polyline;
    private Marker m = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        geocoder = new Geocoder(this, Locale.getDefault());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        botonubi = findViewById(R.id.botonConfirmarUbicacion);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        botonubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent returnIntent = new Intent();
                    if(direccionMarcador==null){
                        returnIntent.putExtra("direccion", direccionGPS);
                    }
                    else{
                        returnIntent.putExtra("direccion", direccionMarcador);
                    }
                    setResult(PedidoActivity.RESULT_OK,returnIntent);
                    finish();


            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }

        inicializarMapa();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9999 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                inicializarMapa();
            }

        }
    }
    @SuppressLint("MissingPermission")
    private void inicializarMapa(){
        myMap.setMyLocationEnabled(true);
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);
        //Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            //latlong = new LatLng(location.getLatitude(),location.getLongitude());
                            posicionGPS = new LatLng(location.getLatitude(),location.getLongitude());
                            try {
                                addresses= (List<Address>) geocoder.getFromLocation(posicionGPS.latitude, posicionGPS.longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            direccionGPS = addresses.get(0).getAddressLine(0);
                            String result = direccionGPS.split(",")[0];
                            direccionGPS=result;
                            crearMarcadorRestaurant();
                        }
                    }
                });

        myMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                latlong = latLng;
                marcador = true;
                try {
                    addresses= (List<Address>) geocoder.getFromLocation(latlong.latitude, latlong.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                direccionMarcador = addresses.get(0).getAddressLine(0);
                String result = direccionMarcador.split(",")[0];
                direccionMarcador=result;
                if(m != null){
                    m.remove();
                }

                MarkerOptions marcador = new MarkerOptions()
                        .alpha(1f)
                        .position(latLng)
                        .draggable(false)
                        .title("Lugar de envio")
                        .snippet("El pedido sera enviado a esta ubicacion");
                m = myMap.addMarker(marcador);
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(0x66FF0000);
                polylineOptions.add(new LatLng(nuevaPosicionResto.latitude, nuevaPosicionResto.longitude));
                polylineOptions.add(latlong);
                polyline.remove();
                polyline= myMap.addPolyline(polylineOptions);

            }
        });


    }

    public void crearMarcadorRestaurant() {
        Random r = new Random();

        // Una direccion aleatoria de 0 a 359 grados
        int direccionRandomEnGrados = r.nextInt(360);

        // Una distancia aleatoria de 100 a 1000 metros
        int distanciaMinima = 100;
        int distanciaMaxima = 1000;
        int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;


        nuevaPosicionResto = SphericalUtil.computeOffset(
                //new LatLng(-31.6395842, -60.6927639),
                posicionGPS,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );
        myMap.addMarker(new MarkerOptions()
                .alpha(1f)
                .position(nuevaPosicionResto)
                //.position(new LatLng(posicionGPS.latitude, posicionGPS.longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_location8))
                .title("Restaurant"));

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(0x66FF0000);
        polylineOptions.add(new LatLng(nuevaPosicionResto.latitude, nuevaPosicionResto.longitude));
        polylineOptions.add(posicionGPS);
        polyline= myMap.addPolyline(polylineOptions);
    }


}
