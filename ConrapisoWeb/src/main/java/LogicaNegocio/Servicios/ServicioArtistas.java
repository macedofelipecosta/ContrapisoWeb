package LogicaNegocio.Servicios;

import LogicaConexion.Interfaces.RepositorioArtista;
import LogicaNegocio.Dominio.Artista;

import java.util.List;

public class ServicioArtistas {
    private static RepositorioArtista repositorioArtistas = new LogicaConexion.Repositorios.RepositorioArtista();


    public Artista nuevoArtista(int id, String nombre){
        return new Artista(nombre);
    }

    public void nuevoArtistaregistrado(Artista artista){
        this.repositorioArtistas.save(artista);
    }

    public Artista buscarArtistaPorID(int id){
        return repositorioArtistas.findById(id);
    }

    public List<Artista> getArtistas() {
        return repositorioArtistas.findAll();
    }

    public Artista eliminarArtista(int id) {
        return repositorioArtistas.deleteById(id);
    }

    public void actualizarArtista(Artista artista) {
        repositorioArtistas.update(artista);
    }
}
