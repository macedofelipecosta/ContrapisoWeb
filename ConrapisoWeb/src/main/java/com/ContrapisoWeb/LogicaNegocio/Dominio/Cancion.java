package com.ContrapisoWeb.LogicaNegocio.Dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cancion_id;
    @Setter
    private String nombre;

    @Setter
    private String url;

    @ManyToMany
    @JoinTable(
            name = "cancion_artista", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "cancion_id"), // Llave foránea de Cancion
            inverseJoinColumns = @JoinColumn(name = "artistaId") // Llave foránea de Artista
    )
    @Setter
    private List<Artista> artistas;

    @ManyToOne
    @JoinColumn(name = "album_id") // Llave foránea en la tabla Cancion
    @Setter
    private Album album;

    public Cancion() {
    }

    public Cancion(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    public void agregarArtista(Artista artista) {
        if (artista == null) {
            throw new NullPointerException("Artista nulo");
        }
        if(confirmarArtista(artista)){
            throw new IllegalArgumentException("Artista ya existente");
        }
        this.artistas.add(artista);
    }

    private boolean confirmarArtista(Artista artista) {
        return this.artistas.contains(artista);
    }
}
