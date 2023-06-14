package com.example.proyectocoches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertarUsuarios extends AppCompatActivity {

    EditText usuarioInsertar,contraseñaInsertar,contraseñaInsertarDos;
    Button loginButton;
    TextView enlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuarios);

        usuarioInsertar = findViewById(R.id.idEditUsuarioRegistro);
        contraseñaInsertar = findViewById(R.id.idEditContraseñaRegistro);
        contraseñaInsertarDos = findViewById(R.id.idEditContraseñaDosRegistro);
        enlace = findViewById(R.id.idTextoEnlaceInsertarUsuario);

        loginButton = findViewById(R.id.insertarButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contraseñaInsertar.getText().toString().equals(contraseñaInsertarDos.getText().toString())) {
                    ejecutarServicio("http://192.168.1.53:80/cochesVenancio/insertar.php");
                }else{
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden, repitelas",Toast.LENGTH_SHORT).show();
                }

            }
        });

        enlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertarUsuarios.this,Login.class);
                startActivity(intent);
            }
        });

    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Usuario Logeado",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            //Obtiene los parametros que se envian en la solicitud HTTP
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombre",usuarioInsertar.getText().toString());
                parametros.put("contrasena",contraseñaInsertar.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}