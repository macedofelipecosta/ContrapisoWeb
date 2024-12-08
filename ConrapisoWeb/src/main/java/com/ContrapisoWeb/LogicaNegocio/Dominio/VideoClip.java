package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class VideoClip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoClip_id;
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "videoclip_artista",
            joinColumns = @JoinColumn(name = "videoclip_id"),
            inverseJoinColumns = @JoinColumn(name = "artistaId")
    )
    private List<Artista> artistas;
    @ManyToMany
    @JoinTable(
            name = "videoclip_album", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "videoclip_id"), // Llave foránea de VideoClip
            inverseJoinColumns = @JoinColumn(name = "album_id") // Llave foránea de Album
    )
    private List<Album> albums;

    private String url;

    public VideoClip(){}

    public VideoClip(String titulo, String url) {
        this.titulo = titulo;
        this.artistas = new ArrayList<>();
        this.url = url;
        this.albums = new ArrayList<>();
    }

    // region Getters

    public int getVideoClip_id() {
        return videoClip_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public String getUrl() {
        return url;
    }


    //endregion

}
