package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
@Table(name = "Artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArtista;

    @Setter
    private String nombre;

    @Setter
    @Embedded
    private Biografia biografia;

    @Setter
    @ManyToOne //relacion N:1
    @JoinColumn(name = "generoMusical_id") //llave foranea
    private GeneroMusical generoMusical;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;

    @ManyToMany(mappedBy = "artistas")
    private List<VideoClip> videoClips;


    public Artista() {
    }

    public Artista(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<Cancion>();
        this.videoClips = new ArrayList<>();
    }

    public Artista(String nombre, Biografia biografia, GeneroMusical generoMusical) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.generoMusical = generoMusical;
        this.canciones = new ArrayList<Cancion>();
        this.videoClips = new ArrayList<>();
    }



}
