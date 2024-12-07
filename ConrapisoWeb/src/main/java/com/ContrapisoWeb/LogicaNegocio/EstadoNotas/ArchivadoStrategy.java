package com.ContrapisoWeb.LogicaNegocio.EstadoNotas;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;

public class ArchivadoStrategy implements EstadoNotasStrategy{

    /**
     * @param nota
     */
    @Override
    public void manejarNota(Nota nota) {
        System.out.println("La nota está archivada.");
    }

    //logica para que se quite automaticamente de la pagina
}
