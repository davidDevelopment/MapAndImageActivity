package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button image_button, find_map_boutton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
        this.overridePendingTransition(R.anim.right_slide_out, R.anim.right_slide_in);

        image_button = findViewById(R.id.image_button);
        image_button.setOnClickListener(this);

        find_map_boutton = findViewById(R.id.find_map_button);
        find_map_boutton.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.find_map_button:
                intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onMapReady(GoogleMap googleMap) {

        //Création d'un point GPS à partir de la latitude et de la longitude
        LatLng sals = new LatLng(47.489666, -0.501235);

        //Création d'un niveau de zoom
        float zoomLevel = (float) 18.0;

        //Selection du type de carte
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Ajout du point GPS sur la carte
        googleMap.addMarker(new MarkerOptions().position(sals).title("Géocache n°1 \n  - Salle 114 -"));

        //Demande d'accés à la permission de localisation GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        }

        //Demande d'accés à la permission de localisation GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1);
        }

        //Activé la localisation du smartphone
        googleMap.setMyLocationEnabled(true);

        //Centrer la carte sur le point GPS crée et régler le zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sals, zoomLevel));

    }
}
