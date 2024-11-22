package LogicaNegocio.Dominio;

public class Cancion{
    private int id;
    private String nombre;
    private int duracion;
    private Album album;

    public Cancion(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion=duracion;
    }

    //region Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Album getAlbum() {
        return album;
    }


    //endregion
}
