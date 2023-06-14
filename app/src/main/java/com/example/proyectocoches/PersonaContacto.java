package com.example.proyectocoches;

public class PersonaContacto {
    String nombre, apellidos, telefono, localizacion, imagen;

    public PersonaContacto(String nombre, String apellidos, String telefono, String localizacion, String imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.localizacion = localizacion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}


