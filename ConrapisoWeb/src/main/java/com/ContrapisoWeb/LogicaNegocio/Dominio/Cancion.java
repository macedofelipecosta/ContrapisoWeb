package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cancion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cancion_id;
    private String nombre;
    private int duracion;
    @ManyToMany
    @JoinTable(
            name = "cancion_artista", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "cancion_id"), // Llave foránea de Cancion
            inverseJoinColumns = @JoinColumn(name = "artistaId") // Llave foránea de Artista
    )
    private List<Artista> artistas;

    @ManyToOne
    @JoinColumn(name = "album_id") // Llave foránea en la tabla Cancion
    private Album album;

    public Cancion(){}
    public Cancion(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion=duracion;
    }

    //region Getters

    public int getCancion_id() {
        return cancion_id;
    }

    public String getNombre() {
        return nombre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }


    //endregion
}
