package LogicaConexion.Repositorios;

import LogicaNegocio.Dominio.Artista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioArtista implements LogicaConexion.Interfaces.RepositorioArtista {

    private static Map<Integer, Artista> artistas = new HashMap<>();

    @Override
    public Artista findById(Integer id) {
        return artistas.get(id);
    }

    @Override
    public Artista findByName(String name) {
        return artistas.values().stream() // Acceder a los valores del HashMap
                .filter(a -> a.getNombre().equalsIgnoreCase(name)) // Filtrar por nombre
                .findFirst() // Tomar el primer resultado
                .orElse(null); // Devolver null si no se encuentra
    }

    @Override
    public List<Artista> findAll() {
        List<Artista> listaArtistas = new ArrayList<>(artistas.values());
        return listaArtistas;
    }

    @Override
    public void save(Artista artista) {
        artistas.put(artista.getIdArtista(), artista);
    }

    @Override
    public void update(Artista artista) {
        deleteById(artista.getIdArtista());
        save(artista);
    }

    @Override
    public Artista deleteById(Integer id) {
        return artistas.remove(id);
    }
}
