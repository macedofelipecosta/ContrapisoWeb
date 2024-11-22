package LogicaNegocio.Dominio;

import LogicaNegocio.EstadoNotas.EstadoNotas;

public class Nota {
private int id;
private String contenido;
private EstadoNotas estado;

    public Nota(String contenido) {
        this.contenido = contenido;
        this.estado = EstadoNotas.Editando;
    }

 //region Getters

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public EstadoNotas getEstado() {
        return estado;
    }


    //endregion
}
