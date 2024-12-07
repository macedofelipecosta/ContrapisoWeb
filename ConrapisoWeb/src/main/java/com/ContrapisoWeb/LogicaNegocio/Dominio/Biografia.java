package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class Biografia {
    private int id;
    private String contenido;

    public Biografia(String contenido) {
        this.contenido = contenido;
    }

    public Biografia() {}


    //region Getters
    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    //endregion

}
