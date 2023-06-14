package com.example.proyectocoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ajustes extends AppCompatActivity {

    Button insertar, eliminar,atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        insertar = findViewById(R.id.buttonInsertarCoche);
        eliminar = findViewById(R.id.buttonEliminarCoche);
        atras = findViewById(R.id.buttonAtrasAjustes);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajustes.this,Inicio.class);
                startActivity(intent);
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajustes.this,InsertarVehiculos.class);
                startActivity(intent);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajustes.this,BorrarVehiculo.class);
                startActivity(intent);
            }
        });

    }
}