package com.example.proyectocoches;

public class VehiculosDos {
    String marca, modelo, combustible, fechaMatriculacion, km, potencia, ubicacion, imagen;
    Double precio;

    public VehiculosDos(String marca, String modelo, String combustible, String fechaMatriculacion, String km, String potencia, String ubicacion, String imagen, Double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.combustible = combustible;
        this.fechaMatriculacion = fechaMatriculacion;
        this.km = km;
        this.potencia = potencia;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(String fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}