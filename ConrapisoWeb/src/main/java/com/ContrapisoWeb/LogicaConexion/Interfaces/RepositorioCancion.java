package com.ContrapisoWeb.LogicaConexion.Interfaces;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCancion extends JpaRepository<Cancion, Integer> {
}
