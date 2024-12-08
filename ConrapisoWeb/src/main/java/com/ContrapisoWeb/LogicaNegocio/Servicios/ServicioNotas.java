package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioArtista;
import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioNota;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import com.ContrapisoWeb.LogicaNegocio.EstadoNotas.EstadoNotas;
import com.ContrapisoWeb.LogicaNegocio.ExcepcionesDominio.ServicioNotasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioNotas {

    @Autowired
    private RepositorioNota repositorioNota;

    @Autowired
    private RepositorioArtista repositorioArtista;

    public Nota nuevaNota(String cuerpo, int artistaId) throws ServicioNotasException {
        if (cuerpo.isEmpty()) {
            throw new ServicioNotasException("El cuerpo de la nota esta vacio!");
        }
        Optional<Artista> artistaOptional = repositorioArtista.findById(artistaId);
        if (artistaOptional.isEmpty()) {
            throw new ServicioNotasException("No se ha proporcionado un artista para la nota!");
        }
        Artista artista = artistaOptional.get();
        Nota nuevaNota = new Nota(cuerpo, artista);
        repositorioNota.save(nuevaNota);
        return nuevaNota;
    }


    public Nota publicarNota(int id) throws ServicioNotasException {
        /*
        *   Busca la nota en la base de datos.
            Valida que la nota está lista para publicarse.
            Actualiza el estado de la nota a PUBLICADO.
            Guarda los cambios en la base de datos.
        *
        */
        Optional<Nota> notaOptional = repositorioNota.findById(id);
        if (notaOptional.isEmpty()) {
            throw new RuntimeException("Nota no encontrada");
        }
        Nota nota = notaOptional.get();

        if (nota.getEstado() != EstadoNotas.EDITANDO) {
            throw new ServicioNotasException("La nota no está en un estado que permita publicarla");
        }

        nota.setEstado(EstadoNotas.PUBLICADO);
        repositorioNota.save(nota);

        return nota;
    }


    public Optional<Nota> findNotaById(int id) {
        Optional<Nota> notaOptional = repositorioNota.findById(id);
        if (notaOptional.isEmpty()) {
            throw new RuntimeException("Nota no encontrada");
        }
        return notaOptional;
    }

    public void actualizarNota(Nota nota) {
        repositorioNota.save(nota);
    }

    public List<Nota> getNotasByArtistaId(int artistaId) throws ServicioNotasException {
        List<Nota> notas = repositorioNota.findByArtista_artistaId(artistaId);
        if (notas.isEmpty()) {
            throw new ServicioNotasException("No se encontraron notas para el artista con ID: " + artistaId);
        }
        return notas;
    }
}
