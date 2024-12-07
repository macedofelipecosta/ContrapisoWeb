package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaNegocio.Dominio.Album;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Cancion;
import com.ContrapisoWeb.LogicaNegocio.Dominio.GeneroMusical;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
  