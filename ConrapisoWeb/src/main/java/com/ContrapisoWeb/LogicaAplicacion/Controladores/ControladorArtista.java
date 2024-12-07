package com.ContrapisoWeb.LogicaAplicacion.Controladores;

import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOGeneroMusical;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Biografia;
import com.ContrapisoWeb.LogicaNegocio.Dominio.GeneroMusical;
import com.ContrapisoWeb.LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class ControladorArtista {
    @Autowired
    Fachada fachada;



    @GetMapping("/newArtista")
    public ResponseEntity<?> createNewArtista(@RequestParam(value = "nombre") String nombre) {
        try {
            //ToDO: Validar que no haya dos artistas con el mismo nombre

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
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                return ResponseEntity.status(404).body("Artista no encontrado.");
            }
            Artista artista= artistaOptional.get();

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

            // Obtener el artistaOptional
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                return ResponseEntity.status(404).body("Artista no encontrado con ID: " + id);
            }

            Artista artista = artistaOptional.get();

            // Actualizar los campos opcionales
            if (nombre != null && !nombre.isBlank()) {
                artista.setNombre(nombre);
            }
            if (biografia != null && !biografia.isBlank()) {
                artista.setBiografia(new Biografia(biografia));
            }
            if (generoMusical != null) { //llega desde la interface como DTO
                GeneroMusical gm = fachada.getGeneroMusical(generoMusical.getNombre()); //ToDo: Si DTOGeneroMusical incluye más campos o validaciones, considera usar una capa de servicio para convertirlo en un objeto de dominio (GeneroMusical).
                if (gm == null) {
                    return ResponseEntity.badRequest().body("El género musical proporcionado no es válido.");
                }
                artista.setGeneroMusical(gm);
            }

            // Guardar los cambios
            fachada.actualizarArtista(artista);

            return ResponseEntity.ok("Artista actualizado con éxito: " + artistaOptional);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/deleteArtista")
    public ResponseEntity<?> eliminarArtista(@RequestParam(value = "id", required = true) int id) {
        try {
            fachada.eliminarArtista(id);
            return ResponseEntity.ok("Artista con id:" + id+ "ha sido eliminado"); //ToDo: ver como veo que artista se elimina
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

}