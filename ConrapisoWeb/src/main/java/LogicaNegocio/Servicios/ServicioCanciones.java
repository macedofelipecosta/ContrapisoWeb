package LogicaNegocio.Servicios;

import LogicaNegocio.Dominio.Album;
import LogicaNegocio.Dominio.Cancion;

import java.util.ArrayList;
import java.util.List;

public class ServicioCanciones {
    private List<Cancion> cancionesList;
    private List<Album> albumList;

    public ServicioCanciones(){
        this.cancionesList = new ArrayList<Cancion>();
        this.albumList= new ArrayList<>();
    }


    private Cancion nuevaCancion(String nombre, int duracion){
        Cancion c= new Cancion(nombre, duracion);
        return c;
    }


    private void agregarNuevaCanvionALista(String nombre, int duracion){
        Cancion c= nuevaCancion(nombre, duracion);
        this.cancionesList.add(c);
    }
}
