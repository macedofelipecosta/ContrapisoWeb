package LogicaConexion.Interfaces;

import LogicaNegocio.Dominio.Artista;

import java.util.List;

public interface RepositorioArtista extends Repositorio<Artista, Integer>{

    @Override
    Artista findById(Integer integer);

    Artista findByName(String name);

    @Override
    List<Artista> findAll();

    @Override
    void save(Artista entity);

    @Override
    void update(Artista entity);

    @Override
    Artista deleteById(Integer integer);
}
