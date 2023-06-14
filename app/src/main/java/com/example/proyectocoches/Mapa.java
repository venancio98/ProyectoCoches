package com.example.proyectocoches;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.proyectocoches.databinding.ActivityMapaBinding;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private Button atras;
    private GoogleMap mMap;
    private ActivityMapaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        atras = findViewById(R.id.buttonAtrasUbicacion);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Mapa.this,Inicio.class);
                startActivity(inte);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng calamonte = new LatLng(38.894443, -6.386141);
        LatLng merida = new LatLng(38.913767, -6.339150);
        LatLng almendralejo = new LatLng(38.692212, -6.407095);
        LatLng sevilla = new LatLng(37.372136, -6.075654);
        LatLng madrid = new LatLng(40.339318, -3.739976);
        mMap.addMarker(new MarkerOptions().position(calamonte).title("Centro Calamonte"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(calamonte));
        mMap.addMarker(new MarkerOptions().position(merida).title("Centro Merida"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(merida));
        mMap.addMarker(new MarkerOptions().position(almendralejo).title("Centro Almendralejo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(almendralejo));
        mMap.addMarker(new MarkerOptions().position(sevilla).title("Centro Sevilla"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sevilla));
        mMap.addMarker(new MarkerOptions().position(madrid).title("Centro Madrid"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));
    }
}