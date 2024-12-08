package com.ContrapisoWeb.LogicaNegocio.Dominio;

import com.ContrapisoWeb.LogicaNegocio.EstadoNotas.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notaId;

    //ToDo: Agregar logica para guardar al autor de la nota;

    @Setter
    private String contenido;

    @Setter
    @ManyToOne // Relación muchos a uno (un artista puede tener varias notas)
    @JoinColumn(name = "artistaId",  referencedColumnName = "artistaId") // Referencia explícita al campo en Artista
    private Artista artista;

    @Setter
    @Enumerated(EnumType.STRING) // Guarda el estado como texto en la base de datos
    private EstadoNotas estado;



    public Nota() {
    }

    public Nota(String contenido, Artista artista) {
        this.contenido = contenido;
        this.artista=artista;
        this.estado = EstadoNotas.EDITANDO;
    }

    public EstadoNotasStrategy getStrategy() {
        switch (estado) {
            case EDITANDO:
                return new EditandoStrategy();
            case PUBLICADO:
                return new PublicadoStrategy();
            case ARCHIVADO:
                return new ArchivadoStrategy();
            default:
                throw new IllegalStateException("Estado no soportado: " + estado);
        }
    }


}
