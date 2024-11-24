package LogicaNegocio.Servicios;

import LogicaNegocio.Dominio.Album;
import LogicaNegocio.Dominio.Cancion;
import LogicaNegocio.Dominio.GeneroMusical;

import java.util.ArrayList;
import java.util.List;

public class ServicioCanciones {
    private List<Cancion> cancionesList;
    private List<Album> albumList;
    private List<GeneroMusical> generosDisponibles;

    public ServicioCanciones(){
        this.cancionesList = new ArrayList<Cancion>();
        this.albumList= new ArrayList<>();
        this.generosDisponibles= new ArrayList<>();
    }


    private Cancion nuevaCancion(String nombre, int duracion){
        Cancion c= new Cancion(nombre, duracion);
        return c;
    }


    private void agregarNuevaCanvionALista(String nombre, int duracion){
        Cancion c= nuevaCancion(nombre, duracion);
        this.cancionesList.add(c);
    }

    public GeneroMusical getGeneroByNombre(String nombre) {
        for (GeneroMusical gm: generosDisponibles){
            if(gm.getNombre().equals(nombre)){
                return  gm;
            }
        }
        return null;
    }
}
