package com.ContrapisoWeb.LogicaAplicacion.DTOs;

public class DTOVideoClip {
    private String titulo;
    private String url;

    public DTOVideoClip(String titulo) {
        this.titulo = titulo;
    }

    public DTOVideoClip(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public String titulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
