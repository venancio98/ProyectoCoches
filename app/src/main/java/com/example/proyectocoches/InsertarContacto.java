package com.example.proyectocoches;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class InsertarContacto extends AppCompatActivity {

    EditText enombre, eapellidos, etelefono, elocalizacion;
    ImageView imagen;
    Button guardar, atras, limpiar, seleccionarImagen;

    Bitmap bitmap;
    int PICK_IMAGE_REQUEST = 1;
    String url = "http://192.168.1.53:80/cochesVenancio/insertarContacto.php";

    String KEY_IMAGE = "imagen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_contacto);

        enombre = findViewById(R.id.etNombre);
        eapellidos = findViewById(R.id.etApellidos);
        etelefono = findViewById(R.id.etTelefono);
        elocalizacion = findViewById(R.id.etLocalidad);

        imagen = findViewById(R.id.idImagenContacto);

        guardar = findViewById(R.id.idButtonGuardarContacto);
        atras = findViewById(R.id.buttonAtrasInsertarContactos);
        limpiar = findViewById(R.id.buttonLimpiarCamposContacto);
        seleccionarImagen = findViewById(R.id.idbuttonSeleccionarImagenContacto);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(InsertarContacto.this,Inicio.class);
                startActivity(inte);
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enombre.setText("");
                eapellidos.setText("");
                etelefono.setText("");
                elocalizacion.setText("");
            }
        });

        seleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarContacto();
            }
        });

    }


    private void InsertarContacto() {
        final ProgressDialog loading = ProgressDialog.show(this,"Guardando Registro...","Espere, por favor");

        //Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(),"Contacto Registrado",Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
             //   Toast.makeText(getApplicationContext(),"ERROR PRIMERO",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String nombre = enombre.getText().toString().trim();
                String apellidos = eapellidos.getText().toString();
                String telefono = etelefono.getText().toString();
                String localizacion = elocalizacion.getText().toString();
                String imagen = getStringImagen(bitmap);

                PersonaContacto persona = new PersonaContacto(nombre,apellidos,telefono,localizacion,imagen);

                Map<String, String> params = new Hashtable<>();
                params.put("nombre",persona.getNombre());
                params.put("apellidos",persona.getApellidos());
                params.put("telefono",persona.getTelefono());
                params.put("localidad",persona.getLocalizacion());
                params.put(KEY_IMAGE, imagen);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    //Inicia la galeria de imagenes y te permite elegir una
    private void showFileChooser() {
        Intent galeria = new Intent();
        galeria.setType("image/*");
        galeria.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galeria,"Selecciona imagen"),PICK_IMAGE_REQUEST);
    }

    //se encarga de coger el objeto bitmap y comprimirlo en formato JPEG, lo codifica en base64 y lo pasa en una cadena.
    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    if (data.getData() != null) {
                        Uri filePath = data.getData();
                        try {
                            //Obtener el mapa de bits de la galeria
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            //Configuracion del mapa de bits
                            imagen.setImageBitmap(bitmap);
                        } catch (IOException e) {
                       //     Toast.makeText(getApplicationContext(), "ERROR SEGUNDO", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
    }
}