package LogicaNegocio.Dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArtista;
    private String nombre;

    @Embedded
    private  Biografia biografia;
    @ManyToOne //relacion N:1
    @JoinColumn(name="generoMusical_id") //llave foranea
    private GeneroMusical generoMusical;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones;

    public List<VideoClip> videoClips() {
        return videoClips;
    }

    public void setVideoClips(List<VideoClip> videoClips) {
        this.videoClips = videoClips;
    }

    public List<Cancion> canciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @ManyToMany(mappedBy = "artistas")
    private List<VideoClip> videoClips;

    public Artista(){}

    public Artista(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<Cancion>();
        this.videoClips= new ArrayList<>();
    }
    public Artista(String nombre, Biografia biografia, GeneroMusical generoMusical) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.generoMusical = generoMusical;
        this.canciones = new ArrayList<Cancion>();
        this.videoClips= new ArrayList<>();
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
