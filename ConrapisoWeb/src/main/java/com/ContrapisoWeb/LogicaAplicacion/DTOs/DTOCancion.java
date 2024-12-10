package com.ContrapisoWeb.LogicaAplicacion.DTOs;

public class DTOCancion {
    private String nombre;
    private int duracion;
    private String url;

    public DTOCancion() {
    }

    public DTOCancion(String nombre) {
        this.nombre = nombre;
    }

    public DTOCancion(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public DTOCancion(String nombre, int duracion, String url) {

        this.nombre = nombre;
        this.duracion = duracion;
        this.url = url;
    }

    public String nombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int duracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
