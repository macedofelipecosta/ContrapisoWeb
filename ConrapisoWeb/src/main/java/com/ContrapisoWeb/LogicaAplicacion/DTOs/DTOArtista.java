package com.ContrapisoWeb.LogicaAplicacion.DTOs;

public class DTOArtista {
    private String nombre;


    public DTOArtista() {}

    public DTOArtista(String nombre) {
        this.nombre = nombre;
    }

    //region Getter

    public String getNombre() {
        return nombre;
    }


    //endregion
}
