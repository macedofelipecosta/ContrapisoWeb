package com.ContrapisoWeb.LogicaNegocio.EstadoNotas;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;

public class EditandoStrategy implements EstadoNotasStrategy{
    /**
     * @param nota
     */
    @Override
    public void manejarNota(Nota nota) {
        System.out.println("La nota está en edición.");
    }

    //logica para que la nota no se publique y quede en estado de espera para seguir siendo editada
}
