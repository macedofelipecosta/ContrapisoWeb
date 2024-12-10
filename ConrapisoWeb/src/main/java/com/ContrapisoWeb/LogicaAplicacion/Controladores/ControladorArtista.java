package com.ContrapisoWeb.LogicaAplicacion.Controladores;

import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOArtista;
import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOCancion;
import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOGeneroMusical;
import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOVideoClip;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Biografia;
import com.ContrapisoWeb.LogicaNegocio.Dominio.GeneroMusical;
import com.ContrapisoWeb.LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ControladorArtista {
    @Autowired
    Fachada fachada;


    @RequestMapping("/newArtista")
    public ResponseEntity<?> createNewArtista(@RequestParam(value = "nombre") String nombre) {
        try {
            // Validar que el nombre no sea nulo o vacío
            if (nombre == null || nombre.isBlank()) {
                return ResponseEntity.badRequest().body("El nombre del artista es obligatorio.");
            }
            // Supongamos que usas un servicio para guardar el artista
            if (fachada.crearArtista(nombre)) {
                return ResponseEntity.ok("Artista creado con éxito: " + nombre);
            } else {
                return ResponseEntity.status(400).body("El artista ya existe o no se pudo crear.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al crear el artista: " + e.getMessage());
        }
    }

    @GetMapping("/getArtistaByName")
    public ResponseEntity<?> getArtistaByName(@RequestParam(value = "nombre", required = true) String nombre) {
        try {
            if (nombre.isEmpty()) {
                throw new RuntimeException("No ha proporcionado ningún nombre!");
            }
            Optional<Artista> artistaOptional = fachada.getArtistaByName(nombre);
            //ToDo: resolver cuando se encuentre más de un artista con ese nombre o combinaciones de letras
            if (artistaOptional.isEmpty()) {
                throw new RuntimeException("No se ha encontrado al artista!");
            }
            Artista artista = artistaOptional.get();
            return ResponseEntity.ok(artista.getNombre());

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Artista no encontrado " + e.getMessage());
        }
    }

    @GetMapping("/getArtistaById") // URL para obtener un artista por ID
    public ResponseEntity<?> getArtistaById(@RequestParam(value = "id", required = false) Integer id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Falta el parámetro 'id'.");
            }
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                return ResponseEntity.status(404).body("Artista no encontrado.");
            }
            Artista artista = artistaOptional.get();

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

    @PutMapping("/editArtista")
    public ResponseEntity<?> editarArtista(@RequestParam(value = "id", required = true) Integer id,
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

            return ResponseEntity.ok("Artista actualizado con éxito: " + artista.getNombre());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @RequestMapping("/deleteArtista")
    public ResponseEntity<?> eliminarArtista(@RequestParam(value = "id", required = true) int id) {
        try {
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                throw new RuntimeException("Artista no encontrado!");
            }
            Artista artista = artistaOptional.get();
            DTOArtista DTOArtista = new DTOArtista(artista.getNombre());
            fachada.eliminarArtista(id);
            return ResponseEntity.ok("El Artista: " + DTOArtista.getNombre() + " ha sido eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/getCancionesArtista")
    public ResponseEntity<?> getCancionesArtista(@RequestParam(value = "id") int id) {
        try {
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                throw new RuntimeException("Artista no encontrado!");
            }
            Artista artista = artistaOptional.get();
            List<DTOCancion> response = artista.getCanciones().stream()
                    .map(c -> new DTOCancion(c.getNombre(), c.getDuracion(), c.getUrl())) // Transforma cada Cancion en un DTOCancion
                    .collect(Collectors.toList()); // Recoge el resultado en una lista

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Error interno: " + ex.getMessage());
        }
    }

    @GetMapping("/getVideoClipsArtista")
    public ResponseEntity<?> getVideoClipsArtista(@RequestParam(value = "id") int id) {
        try {
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(id);
            if (artistaOptional.isEmpty()) {
                throw new RuntimeException("Artista no encontrado!");
            }
            Artista artista = artistaOptional.get();
            List<DTOVideoClip> response = artista.getVideoClips().stream()
                    .map(vc -> new DTOVideoClip(vc.getTitulo(), vc.getUrl())) // Transforma cada vc en un DTOVideoClip
                    .collect(Collectors.toList()); // Recoge el resultado en una lista

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Error interno: " + ex.getMessage());
        }
    }
}