package LogicaAplicacion.Controladores;

import LogicaNegocio.Dominio.Artista;
import LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorArtista {
    @Autowired
    Fachada fachada;

    @GetMapping("/artista") // URL para obtener un artista por ID
    public ResponseEntity<?> getArtista(@RequestParam(value = "id", required = false) Integer id) {

        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Falta el parámetro 'id'.");
            }
            Artista artista = fachada.getArtistaPorId(id);
            if (artista == null) {
                return ResponseEntity.status(404).body("Artista no encontrado.");
            }
            return ResponseEntity.ok(artista); // Devuelve el artista como JSON
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }


    @GetMapping("/newArtista")
    public ResponseEntity<?> createNewArtista(@RequestParam(value = "id") Integer id,
                                              @RequestParam(value = "nombre") String nombre) {
        try {
            // Aquí llamas a la lógica para crear un nuevo artista
            Artista nuevoArtista = new Artista(id, nombre);

            // Supongamos que usas un servicio para guardar el artista
            fachada.crearArtista(nuevoArtista);

            return ResponseEntity.ok("Artista creado con éxito: " + nuevoArtista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el artista: " + e.getMessage());
        }

    }
}