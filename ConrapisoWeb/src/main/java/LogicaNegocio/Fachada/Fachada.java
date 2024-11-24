package LogicaNegocio.Fachada;

import LogicaNegocio.Dominio.Artista;
import LogicaNegocio.Dominio.GeneroMusical;
import LogicaNegocio.Servicios.ServicioArtistas;
import LogicaNegocio.Servicios.ServicioCanciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Fachada {
    private static Fachada fachada = new Fachada();
    ServicioArtistas sa = new ServicioArtistas();
    ServicioCanciones sc = new ServicioCanciones();

    public static synchronized Fachada getInstancia() {
        if (fachada != null) {
            return fachada;
        } else {
            return fachada = new Fachada();
        }
    }


    public Artista getArtistaPorId(int id) {
        return sa.buscarArtistaPorID(id);
    }

    public void nuevoArtista(int id, String nombre) {
        sa.nuevoArtistaregistrado(sa.nuevoArtista(id, nombre));
    }

    public void crearArtista(Artista nuevoArtista) {
        sa.nuevoArtistaregistrado(nuevoArtista);
    }

    public List<Artista> getArtistas() {
        return sa.getArtistas();
    }

    public GeneroMusical getGeneroMusical(String nombre) {
        return sc.getGeneroByNombre(nombre);
    }

    public Artista eliminarArtista(int id) {
        return sa.eliminarArtista(id);
    }

    public void actualizarArtista(Artista artista) {
        sa.actualizarArtista(artista);
    }
}
