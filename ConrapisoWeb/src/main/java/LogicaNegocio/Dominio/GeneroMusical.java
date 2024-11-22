package LogicaNegocio.Dominio;

public class GeneroMusical {
    private int id;
    private String nombre;

    public GeneroMusical(String nombre) {
        this.nombre = nombre;
    }

    //region Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    //endregion



}
