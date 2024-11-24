package LogicaNegocio.Dominio;

public class Artista {
    private int idArtista;
    private String nombre;
    private  Biografia biografia;
    private GeneroMusical generoMusical;

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


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setBiografia(String biografia){
        this.biografia= new Biografia(biografia);
    }
    public void setGeneroMusical(GeneroMusical genero){
        this.generoMusical=genero;
    }
}
