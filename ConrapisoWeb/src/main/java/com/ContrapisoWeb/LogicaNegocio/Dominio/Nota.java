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
    private int nota_id;

    @Setter
    private String contenido;

    @Setter
    @ManyToOne // Relación muchos a uno (un artista puede tener varias notas)
    @JoinColumn(name = "artista_id") // Llave foránea en la tabla Nota
    private Artista artista;

    @Setter
    @Enumerated(EnumType.STRING) // Guarda el estado como texto en la base de datos
    private EstadoNotas estado;



    public Nota() {
    }

    public Nota(String contenido) {
        this.contenido = contenido;
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
