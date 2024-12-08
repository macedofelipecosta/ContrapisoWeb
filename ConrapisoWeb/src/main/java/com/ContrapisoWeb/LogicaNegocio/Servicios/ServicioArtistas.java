package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioArtista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioArtistas {
    //ToDo: crear excepciones especificas para controladores, etc...

    @Autowired
    private  RepositorioArtista repositorioArtistas;


    public boolean nuevoArtistaRegistrado(String nombre){
        // Verificar si ya existe un artista con el mismo nombre
        Optional<Artista> artistaExistente = repositorioArtistas.findByNombre(nombre);

        if (artistaExistente.isPresent()) {
           return false;
        }
        // Crear y guardar el nuevo artista
        Artista nuevoArtista = new Artista(nombre);
        repositorioArtistas.save(nuevoArtista);
        return true;
    }

    public Optional<Artista> buscarArtistaPorID(int id){
        return repositorioArtistas.findById(id);
    }

    public Optional<Artista> buscarArtistaPorNombre(String nombre){
        return repositorioArtistas.findByNombre(nombre);
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
