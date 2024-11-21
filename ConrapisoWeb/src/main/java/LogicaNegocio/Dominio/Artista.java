package LogicaNegocio.Dominio;

public class Artista {
    private int idArtista;
    private String nombre;

    public Artista(int idArtista, String nombre) {
        this.idArtista = idArtista;
        this.nombre = nombre;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getNombre() {
        return nombre;
    }
}
