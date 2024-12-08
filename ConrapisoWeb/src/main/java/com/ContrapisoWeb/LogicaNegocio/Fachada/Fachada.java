package com.ContrapisoWeb.LogicaNegocio.Fachada;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioNota;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.GeneroMusical;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import com.ContrapisoWeb.LogicaNegocio.ExcepcionesDominio.ServicioNotasException;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioArtistas;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioCanciones;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioNotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Fachada {


    private static Fachada fachada = new Fachada();
    @Autowired
    ServicioArtistas sa;
    ServicioCanciones sc;
    @Autowired
    ServicioNotas sn;
    @Autowired
    private RepositorioNota repositorioNota;

    public static synchronized Fachada getInstancia() {
        if (fachada != null) {
            return fachada;
        } else {
            return fachada = new Fachada();
        }
    }

    //region ARTISTAS
    public Optional<Artista> getArtistaPorId(int id) {
        return sa.buscarArtistaPorID(id);
    }

    public boolean crearArtista(String nombre) {
        return sa.nuevoArtistaRegistrado(nombre);
    }

    public List<Artista> getArtistas() {
        return sa.getArtistas();
    }

    public void eliminarArtista(int id) {
        sa.eliminarArtista(id);
    }

    public void actualizarArtista(Artista artista) {
        sa.actualizarArtista(artista);
    }

    public Optional<Artista> getArtistaByName(String nombre) {
        return sa.buscarArtistaPorNombre(nombre);
    }

    //endregion
    //region NOTAS
    public void nuevaNota(String cuerpo, int artistaId) throws ServicioNotasException {
        sn.nuevaNota(cuerpo, artistaId);
    }

    public Nota publicarNota(int id) throws ServicioNotasException {
        return sn.publicarNota(id);
    }

    public Optional<Nota> findNotaById(int id) {
        return sn.findNotaById(id);
    }

    public void actualizarNota(Nota nota) {
        sn.actualizarNota(nota);
    }
    public List<Nota> getNotasByArtistaId(int artistaId) throws ServicioNotasException {
        return sn.getNotasByArtistaId(artistaId);
    }
    //endregion

    //region GENERO MUSICAL
    public GeneroMusical getGeneroMusical(String nombre) {
        return sc.getGeneroByNombre(nombre);
    }

    //endregion

}
