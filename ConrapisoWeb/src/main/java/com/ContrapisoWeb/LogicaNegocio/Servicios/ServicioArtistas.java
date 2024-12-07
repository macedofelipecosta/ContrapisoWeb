package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioArtista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioArtistas {
    @Autowired
    private  RepositorioArtista repositorioArtistas;


    public void nuevoArtistaRegistrado(Artista artista){
        repositorioArtistas.save(artista);
    }

    public Optional<Artista> buscarArtistaPorID(int id){
        return repositorioArtistas.findById(id);
    }

    public List<Artista> getArtistas() {
        return repositorioArtistas.findAll();
    }

    public void eliminarArtista(int id) {
        repositorioArtistas.deleteById(id);
    }

    public void actualizarArtista(Artista artista) {
        repositorioArtistas.save(artista);
    }
}
