package com.example.proyectocoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BorrarVehiculo extends AppCompatActivity {

    Button botonAtras, botonEliminar;
    EditText editMarca, editModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_vehiculo);

        botonAtras = findViewById(R.id.buttonAtrasBorrarVehiculos);
        botonEliminar = findViewById(R.id.buttonEliminarVehiculo);
        editMarca = findViewById(R.id.editBorrarVehiculoMarca);
        editModelo = findViewById(R.id.editBorrarVehiculoModelo);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(BorrarVehiculo.this,Ajustes.class);
                startActivity(inte);
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarVehiculo("http://192.168.1.53:80/cochesVenancio/eliminarVehiculo.php?marca="+editMarca.getText()+"&modelo="+editModelo.getText());
            }
        });



    }


    private void eliminarVehiculo(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(),editMarca.getText().toString()+", "+editModelo.getText().toString()+":EXITOSO",Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),editMarca.getText().toString()+", "+editModelo.getText().toString()+":Borrado",Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}