package com.ContrapisoWeb.LogicaConexion.Interfaces;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioArtista extends JpaRepository<Artista, Integer> {

    Optional<Artista> findByNombre(String nombre);
}
