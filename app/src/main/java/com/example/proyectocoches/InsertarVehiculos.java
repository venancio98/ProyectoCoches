package com.example.proyectocoches;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class InsertarVehiculos extends AppCompatActivity {

    EditText eMarca, eModelo, eCombustible, eFechaMatriculacion, eKm, ePotencia, eUbicacion, ePrecio;
    ImageView ivImagen;
    Button guardar, seleccionar,botonLimpiar,botonAtras;

    Bitmap bitmap;
    int PICK_IMAGE_REQUEST = 1;
    String url = "http://192.168.1.53:80/cochesVenancio/insertar_vehiculo.php";

    String KEY_IMAGE = "imagen";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_vehiculos);

        botonAtras = findViewById(R.id.buttonAtrasInsertarVehiculos);
        botonLimpiar=findViewById(R.id.buttonLimpiarCampos);
        eMarca = findViewById(R.id.etNombre);
        eModelo = findViewById(R.id.etApellidos);
        eCombustible = findViewById(R.id.etTelefono);
        eFechaMatriculacion = findViewById(R.id.etLocalidad);
        eKm = findViewById(R.id.etKm);
        ePotencia = findViewById(R.id.etPotencia);
        eUbicacion = findViewById(R.id.etUbicacion);
        ePrecio = findViewById(R.id.etPrecio);


        guardar = findViewById(R.id.idButtonGuardar);
        seleccionar = findViewById(R.id.idbuttonSeleccionar);
        ivImagen = findViewById(R.id.idImagenContacto);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(InsertarVehiculos.this,Inicio.class);
                startActivity(inte);
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eMarca.setText("");
                eModelo.setText("");
                eCombustible.setText("");
                eFechaMatriculacion.setText("");
                eKm.setText("");
                ePotencia.setText("");
                eUbicacion.setText("");
                ePrecio.setText("");
            }
        });

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertarVehiculo();
            }
        });
    }

    private void InsertarVehiculo() {
        final ProgressDialog loading = ProgressDialog.show(this,"Guardando Registro...","Espere, por favor");

        //Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"ERROR"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String marca = eMarca.getText().toString().trim();
                String modelo = eModelo.getText().toString();
                String combustible = eCombustible.getText().toString();
                String fechaMatriculacion = eFechaMatriculacion.getText().toString();
                String km = eKm.getText().toString().trim();
                String potencia = ePotencia.getText().toString();
                String ubicacion = eUbicacion.getText().toString();
                double precio = Double.parseDouble(ePrecio.getText().toString());
                String imagen = getStringImagen(bitmap);

                Vehiculo vehi = new Vehiculo(marca,modelo,combustible,fechaMatriculacion,km,potencia,ubicacion,imagen,precio);


                Map<String, String> params = new Hashtable<>();
                params.put("marca",vehi.getMarca());
                params.put("modelo",vehi.getModelo());
                params.put("combustible",vehi.getCombustible());
                params.put("fechaMatriculacion",vehi.getFechaMatriculacion());
                params.put("km",vehi.getKm());
                params.put("potencia",vehi.getPotencia());
                params.put("ubicacion",vehi.getUbicacion());
                params.put("precio",String.valueOf(vehi.getPrecio()));
                params.put(KEY_IMAGE, imagen);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent galeria = new Intent();
        galeria.setType("image/*");
        galeria.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galeria,"Selecciona imagen"),PICK_IMAGE_REQUEST);
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
                            ivImagen.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "ERROR" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
    }
}