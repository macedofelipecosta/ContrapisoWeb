package com.ContrapisoWeb.LogicaNegocio.EstadoNotas;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;

public class PublicadoStrategy implements EstadoNotasStrategy{
    /**
     * @param nota
     */
    @Override
    public void manejarNota(Nota nota) {
        System.out.println("La nota se publica automáticamente en la página.");
        // Simular actualización del estado en un servicio externo
        System.out.println("Actualizando estado en el sistema externo...");
        nota.setEstado(EstadoNotas.PUBLICADO);
    }

    //logica para que se publique automaticamente en la pagina
}
