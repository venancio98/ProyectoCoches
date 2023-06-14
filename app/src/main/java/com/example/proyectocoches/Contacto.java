package com.example.proyectocoches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Contacto extends AppCompatActivity  {
    Button atras, addContacto, cargarContactos;

    private RecyclerView recyclerViewContacto;
    private RecyclerViewAdaptadorContacto adaptadorContacto;
    private Context context = this;
    private List<PersonaContacto> listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        listaPersonas = buscarContacto("http://192.168.1.53:80/cochesVenancio/mostrarContactos.php");



        atras = findViewById(R.id.buttonAtrasContacto);
        addContacto = findViewById(R.id.buttonAddContacto);
        cargarContactos = findViewById(R.id.buttonCargarContactos);

        addContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Contacto.this,InsertarContacto.class);
                startActivity(inte);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Contacto.this,Inicio.class);
                startActivity(inte);
            }
        });

        cargarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewContacto = findViewById(R.id.idRecyclerContacto);
                recyclerViewContacto.setLayoutManager(new LinearLayoutManager(context));

                adaptadorContacto = new RecyclerViewAdaptadorContacto(listaPersonas);
                recyclerViewContacto.setAdapter(adaptadorContacto);

               LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(context);
               //Agrega lineas divisorias entre los elementos
               DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, myLinearLayoutManager.getOrientation());
               recyclerViewContacto.addItemDecoration(dividerItemDecoration);
            }
        });
    }


    private List<PersonaContacto> buscarContacto(String URL) {
        List<PersonaContacto> listaContactos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        response.length();
                        jsonObject = response.getJSONObject(i);
                        String enombre = jsonObject.getString("nombre");
                        String eapellidos = jsonObject.getString("apellidos");
                        String etelefono = jsonObject.getString("telefono");
                        String eLocalizacion = jsonObject.getString("localidad");
                        String imagen = jsonObject.getString("imagen");

                        PersonaContacto personaContacto = new PersonaContacto(enombre,eapellidos,etelefono,eLocalizacion,imagen);
                        listaContactos.add(personaContacto);


                    } catch (JSONException e) {
                        Toast.makeText(Contacto.this, "No se encuentra", Toast.LENGTH_SHORT).show();
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
        return listaContactos;
    }


}