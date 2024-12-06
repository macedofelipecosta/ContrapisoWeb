package LogicaAplicacion.Controladores;

import LogicaAplicacion.DTOs.DTOGeneroMusical;
import LogicaNegocio.Dominio.Artista;
import LogicaNegocio.Dominio.GeneroMusical;
import LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorArtista {
    @Autowired
    Fachada fachada;

    @GetMapping("/newArtista")
    public ResponseEntity<?> createNewArtista(@RequestParam(value = "id") Integer id,
                                              @RequestParam(value = "nombre") String nombre) {
        try {
            // Aquí llamas a la lógica para crear un nuevo artista
            Artista nuevoArtista = new Artista(nombre);

            // Supongamos que usas un servicio para guardar el artista
            fachada.crearArtista(nuevoArtista);

            return ResponseEntity.ok("Artista creado con éxito: " + nuevoArtista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el artista: " + e.getMessage());
        }
    }

    @GetMapping("/getArtista") // URL para obtener un artista por ID
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

    @GetMapping("/getArtistas") // URL para obtener todos los artistas registrados en el sistema
    public ResponseEntity<?> getArtistas() {
        try {
            List<Artista> artistas = fachada.getArtistas();
            if (artistas.isEmpty()) {
                return ResponseEntity.status(404).body("No se encuentran artistas.");
            }
            return ResponseEntity.ok(artistas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/editArtista")
    public ResponseEntity<?> editarArtista(@RequestParam(value = "id") Integer id,
                                           @RequestParam(value = "nombre", required = false) String nombre,
                                           @RequestParam(value = "biografia", required = false) String biografia,
                                           @RequestParam(value = "generoMusical", required = false) DTOGeneroMusical generoMusical) {
        try {
            // Validación del ID
            if (id == null) {
                return ResponseEntity.badRequest().body("El parámetro 'id' es obligatorio.");
            }

            // Obtener el artista
            Artista artista = fachada.getArtistaPorId(id);
            if (artista == null) {
                return ResponseEntity.status(404).body("Artista no encontrado con ID: " + id);
            }

            // Actualizar los campos opcionales
            if (nombre != null && !nombre.isBlank()) {
                artista.setNombre(nombre);
            }
            if (biografia != null && !biografia.isBlank()) {
                artista.setBiografia(biografia);
            }
            if (generoMusical != null) { //llega desde la interface como DTO
                GeneroMusical gm = fachada.getGeneroMusical(generoMusical.getNombre());
                if (gm == null) {
                    return ResponseEntity.badRequest().body("El género musical proporcionado no es válido.");
                }
                artista.setGeneroMusical(gm);
            }

            // Guardar los cambios
            fachada.actualizarArtista(artista);

            return ResponseEntity.ok("Artista actualizado con éxito: " + artista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/deleteArtista")
    public ResponseEntity<?> eliminarArtista(@RequestParam(value = "id", required = true) int id) {
        try {
            Artista artista = fachada.eliminarArtista(id);
            return ResponseEntity.ok(artista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

}