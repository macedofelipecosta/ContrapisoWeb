package com.ContrapisoWeb.LogicaAplicacion.DTOs;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;

public class DTONota {
    private DTOArtista artista;
    private String contenido;
    private String estado;


    public DTONota(DTOArtista artista) {
        this.artista = artista;
    }

    public DTONota(DTOArtista artista, String contenido) {
        this.artista = artista;
        this.contenido = contenido;
    }

    public DTONota(DTOArtista artista, String contenido, String estado) {
        this.artista = artista;
        this.contenido = contenido;
        this.estado = estado;
    }

    public DTOArtista getArtista() {
        return artista;
    }

    public void setArtista(DTOArtista artista) {
        this.artista = artista;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
