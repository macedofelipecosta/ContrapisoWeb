package LogicaNegocio.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private List<Cancion> canciones;
    private List<VideoClip> videoClips;

    public Album(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.canciones = new ArrayList<>();
        this.videoClips = new ArrayList<>();
    }

    //region Getters

    public int id() {
        return id;
    }

    public String nombre() {
        return nombre;
    }

    public String descripcion() {
        return descripcion;
    }

    public String imagen() {
        return imagen;
    }

    public List<Cancion> canciones() {
        return canciones;
    }

    public List<VideoClip> videoClips() {
        return videoClips;
    }

    //endregion


}
