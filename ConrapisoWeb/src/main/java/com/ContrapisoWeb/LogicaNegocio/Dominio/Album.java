package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int album_id;
    private String nombre;
    private String descripcion;
    private String imagen;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones;

    @ManyToMany
    @JoinTable(
            name = "album_artista",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;

    @ManyToMany(mappedBy = "albums") // Relación inversa
    private List<VideoClip> videoClips;

    public Album(){}
    public Album(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.canciones = new ArrayList<>();
        this.videoClips = new ArrayList<>();
        this.artistas = new ArrayList<>();
    }

    //region Getters

    public int id() {
        return album_id;
    }

    public String nombre() {
        return nombre;
    }

    public String descripcion() {
        return descripcion;
    }

    public String imagen() {
        return imagen;
    }

    public List<Cancion> canciones() {
        return canciones;
    }

    public List<VideoClip> videoClips() {
        return videoClips;
    }

    //endregion


}
