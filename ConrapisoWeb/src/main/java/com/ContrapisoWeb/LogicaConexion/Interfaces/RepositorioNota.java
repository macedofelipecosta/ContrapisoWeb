package com.ContrapisoWeb.LogicaConexion.Interfaces;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioNota extends JpaRepository<Nota, Integer> {
    List<Nota> findByArtista_artistaId(int artistaId);
}
