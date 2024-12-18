package com.ContrapisoWeb.LogicaAplicacion.DTOs;

public class DTOCancion {
    private String nombre;
    private String url;

    public DTOCancion() {
    }

    public DTOCancion(String nombre) {
        this.nombre = nombre;
    }

    public DTOCancion(String nombre, String url) {

        this.nombre = nombre;
        this.url = url;
    }

    public String nombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
