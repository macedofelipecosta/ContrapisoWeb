package com.ContrapisoWeb.LogicaAplicacion.Controladores;


import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTOArtista;
import com.ContrapisoWeb.LogicaAplicacion.DTOs.DTONota;
import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioNota;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import com.ContrapisoWeb.LogicaNegocio.ExcepcionesDominio.ServicioNotasException;
import com.ContrapisoWeb.LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ControladorNota {
    @Autowired
    private Fachada fachada;
    @Autowired
    private RepositorioNota repositorioNota;


    @RequestMapping("/nuevaNota")
    public ResponseEntity<?> nuevaNota(@RequestParam(value = "cuerpo") String cuerpo
            , @RequestParam(value = "artistaId") Integer artistaId) {
        try {
            fachada.nuevaNota(cuerpo, artistaId);
            return ResponseEntity.ok("La nota se ha creado con éxito!");
        } catch (ServicioNotasException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @RequestMapping("/publicarNota")
    public ResponseEntity<?> publicarNota(@RequestParam(value = "notaId") int id) {
        try {
            Nota notaPublicada = fachada.publicarNota(id);
            return ResponseEntity.ok(notaPublicada.getContenido());
        } catch (ServicioNotasException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @RequestMapping("/editarNota")
    public ResponseEntity<?> editarNota(@RequestParam(value = "notaId") int id,
                                        @RequestParam(value = "cuerpo") String cuerpo,
                                        @RequestParam(value = "idArtista", required = false) int artistaId) {
        try {
            Optional<Nota> notaOptional = fachada.findNotaById(id);
            if (notaOptional.isEmpty()) {
                return ResponseEntity.status(404).body("No se ha encontrado la nota con ese id!");
            }
            Nota nota = notaOptional.get();

            if (!cuerpo.isEmpty() || !cuerpo.isBlank()) {
                nota.setContenido(cuerpo);
            }
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(artistaId);
            if (artistaOptional.isPresent()) {
                Artista artista = artistaOptional.get();
                nota.setArtista(artista);
            }
            fachada.actualizarNota(nota);
            return ResponseEntity.ok("La nota ha sido editada correctamente!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null); // Maneja errores de manera adecuada
        }
    }

    @RequestMapping("/getNotasByArtista")
    public ResponseEntity<?> getNotasByArtistaId(@RequestParam(value = "artistaId") int artistaId) {
        try {
            List<Nota> notas = fachada.getNotasByArtistaId(artistaId);
            List<DTONota> response = notas.stream()
                    .map(n -> new DTONota(new DTOArtista(n.getArtista().getNombre()), n.getContenido(), n.getEstado().toString()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (ServicioNotasException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @DeleteMapping("/deleteNotaById")
    public ResponseEntity<?> deleteNotaById(@RequestParam(value="notaId") int notaId){
        try{
            Optional<Nota> notaOptional = fachada.findNotaById(notaId);
            if (notaOptional.isEmpty()) {
                return ResponseEntity.status(404).body("No se ha encontrado la nota con ese id!");
            }
            Nota nota = notaOptional.get();
            repositorioNota.delete(nota);
            return  ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado la nota correctamente!");
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

}
