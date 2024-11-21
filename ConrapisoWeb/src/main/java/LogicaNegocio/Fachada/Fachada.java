package LogicaNegocio.Fachada;

import LogicaNegocio.Dominio.Artista;
import LogicaNegocio.Servicios.ServicioArtistas;
import org.springframework.stereotype.Service;

@Service
public class Fachada {
    private static Fachada fachada = new Fachada();
    ServicioArtistas sa = new ServicioArtistas();


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
}
