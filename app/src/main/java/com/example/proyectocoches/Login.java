package com.example.proyectocoches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    TextView enlace;
    EditText usuarioLogin,contraseñaLogin;
    Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioLogin = findViewById(R.id.idEditUsuarioLogin);
        contraseñaLogin = findViewById(R.id.idEditContraseñaLogin);
        enlace = findViewById(R.id.idTextoEnlace);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario("http://192.168.1.53:80/cochesVenancio/buscar.php?nombre="+usuarioLogin.getText()+"");
            }
        });

        enlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,InsertarUsuarios.class);
                startActivity(intent);
            }
        });
    }
    private void buscarUsuario(String URL){
        //Obtenemos el JSON, de esta url.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++){
                    try {
                        //Obtengo el objeto JSON en la posicion indicada
                        jsonObject=response.getJSONObject(i);
                       String usuario = jsonObject.getString("nombre");
                       String contrasena = jsonObject.getString("contrasena");
                        Toast.makeText(Login.this, "¡¡Bienvenido "+usuario.toString()+"!!", Toast.LENGTH_SHORT).show();
                       if(usuario.toString().equals(usuarioLogin.getText().toString())&&contrasena.toString().equals(contraseñaLogin.getText().toString())){
                           Intent intent = new Intent(Login.this,Inicio.class);
                           startActivity(intent);
                        }else{
                           Toast.makeText(Login.this, "No se encuentran los datos", Toast.LENGTH_SHORT).show();
                       }

                    }catch (JSONException e){
                        Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );
        //se utiliza para enviar las solicitudes HTTP realizadas por la aplicación.
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}