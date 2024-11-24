package LogicaConexion.Interfaces;

import LogicaNegocio.Dominio.Artista;

import java.util.List;

public interface Repositorio <T, ID> {
    T findById(ID id);
    T findByName(String name);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    T deleteById(ID id);

}
