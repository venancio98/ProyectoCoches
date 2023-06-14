package com.example.proyectocoches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ActivityGarage extends AppCompatActivity {

    private Button botonCargar, botonAtras;
    private RecyclerView recyclerViewVehiculo;
    private RecyclerViewAdaptador adaptadorVehiculo;
    private Context context = this;
    private List<VehiculosDos> listaVehiculos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        //Se encarga de darnos la lista con los objetos de vehículos que tenemos en nuestra base de datos.
        listaVehiculos = buscarUsuario("http://192.168.1.53:80/cochesVenancio/mostrarVehiculos.php");

        botonAtras = findViewById(R.id.buttonAtrasGaraje);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(ActivityGarage.this,Inicio.class);
                startActivity(inte);
            }
        });

        botonCargar = findViewById(R.id.idButtonCargarCoches);
        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewVehiculo = findViewById(R.id.idRecyclerVehiculos);
                recyclerViewVehiculo.setLayoutManager(new LinearLayoutManager(context));

                adaptadorVehiculo = new RecyclerViewAdaptador(listaVehiculos);
                recyclerViewVehiculo.setAdapter(adaptadorVehiculo);

                LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(context);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, myLinearLayoutManager.getOrientation());
                recyclerViewVehiculo.addItemDecoration(dividerItemDecoration);
            }
        });




    }


    private List<VehiculosDos> buscarUsuario(String URL) {
        List<VehiculosDos> listaUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        response.length();
                        jsonObject = response.getJSONObject(i);
                        String emarca = jsonObject.getString("marca");
                        String emodelo = jsonObject.getString("modelo");
                        String ecombustible = jsonObject.getString("combustible");
                        String efechaMatriculacion = jsonObject.getString("fechaMatriculacion");
                        String ekm = jsonObject.getString("km");
                        String potencia = jsonObject.getString("potencia");
                        String ubicacion = jsonObject.getString("ubicacion");
                        String imagen = jsonObject.getString("imagen");
                        Double precio = jsonObject.getDouble("precio");

                        VehiculosDos user = new VehiculosDos(emarca, emodelo, ecombustible, efechaMatriculacion, ekm, potencia, ubicacion, imagen, precio);
                        listaUsuarios.add(user);


                    } catch (JSONException e) {
                        Toast.makeText(ActivityGarage.this, "No se encuentra", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
        return listaUsuarios;
    }


}