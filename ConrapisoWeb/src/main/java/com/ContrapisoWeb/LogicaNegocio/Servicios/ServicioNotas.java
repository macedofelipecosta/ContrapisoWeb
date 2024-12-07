package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioNota;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import com.ContrapisoWeb.LogicaNegocio.EstadoNotas.EstadoNotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioNotas {

    @Autowired
    private RepositorioNota repositorioNota;

    public Nota publicarNota(int id) {
        Optional<Nota> notaOptional = repositorioNota.findById(id);
        if (notaOptional.isEmpty()){
            throw  new RuntimeException("Nota no encontrada");
        }
        Nota nota= notaOptional.get();

        if (nota.getEstado() != EstadoNotas.EDITANDO) {
            throw new RuntimeException("La nota no está en un estado que permita publicarla");
        }

        nota.setEstado(EstadoNotas.PUBLICADO);
        repositorioNota.save(nota);

        return nota;
    }


    /*
    *   Busca la nota en la base de datos.
        Valida que la nota está lista para publicarse.
        Actualiza el estado de la nota a PUBLICADO.
        Guarda los cambios en la base de datos.
    *
    * */
}
