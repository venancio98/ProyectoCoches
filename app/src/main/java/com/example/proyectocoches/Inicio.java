package com.example.proyectocoches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {
    ImageButton home,ajustes, ubicacion, garaje, informacion, contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        home = findViewById(R.id.buttonHome);
        ajustes = findViewById(R.id.buttonAjustes);
        ubicacion = findViewById(R.id.buttonUbicacion);
        garaje = findViewById(R.id.buttonGaraje);
        informacion = findViewById(R.id.buttonInformacion);
        contacto = findViewById(R.id.buttonContacto);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this,Login.class);
                startActivity(inte);
            }
        });

        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this,Ajustes.class);
                startActivity(inte);
            }
        });

        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this,Mapa.class);
                startActivity(inte);
            }
        });

        garaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this, ActivityGarage.class);
                startActivity(inte);
            }
        });

        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this,Informacion.class);
                startActivity(inte);
            }
        });

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Inicio.this,Contacto.class);
                startActivity(inte);
            }
        });

    }
}