package LogicaNegocio.Servicios;

import LogicaNegocio.Dominio.Artista;

import java.util.ArrayList;
import java.util.List;

public class ServicioArtistas {
    private List<Artista> artistasRegistrados=new ArrayList<Artista>();




    public Artista nuevoArtista(int id, String nombre){
        return new Artista(id, nombre);
    }

    public void nuevoArtistaregistrado(Artista artista){
        this.artistasRegistrados.add(artista);
    }

    public Artista buscarArtistaPorID(int id){
        for(Artista a:artistasRegistrados){
            if (a.getIdArtista()==id){
                return a;
            }
        }
        return null;
    }
}
