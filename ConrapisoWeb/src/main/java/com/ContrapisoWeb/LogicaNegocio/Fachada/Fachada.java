package com.ContrapisoWeb.LogicaNegocio.Fachada;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.GeneroMusical;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioArtistas;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioCanciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Fachada {
    private static Fachada fachada = new Fachada();
    @Autowired
    ServicioArtistas sa = new ServicioArtistas();
    @Autowired
    ServicioCanciones sc = new ServicioCanciones();

    public static synchronized Fachada getInstancia() {
        if (fachada != null) {
            return fachada;
        } else {
            return fachada = new Fachada();
        }
    }


    public Optional<Artista> getArtistaPorId(int id) {
        return sa.buscarArtistaPorID(id);
    }

    public void crearArtista(Artista nuevoArtista) {
        sa.nuevoArtistaRegistrado(nuevoArtista);
    }

    public List<Artista> getArtistas() {
        return sa.getArtistas();
    }

    public GeneroMusical getGeneroMusical(String nombre) {
        return sc.getGeneroByNombre(nombre);
    }

    public void eliminarArtista(int id) {
         sa.eliminarArtista(id);
    }

    public void actualizarArtista(Artista artista) {
        sa.actualizarArtista(artista);
    }
}
