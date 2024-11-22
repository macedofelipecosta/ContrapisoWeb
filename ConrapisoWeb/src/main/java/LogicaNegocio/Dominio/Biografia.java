package LogicaNegocio.Dominio;

public class Biografia {
    private int id;
    private String contenido;

    public Biografia(String contenido) {
        this.contenido = contenido;
    }


    //region Getters
    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    //endregion

}
