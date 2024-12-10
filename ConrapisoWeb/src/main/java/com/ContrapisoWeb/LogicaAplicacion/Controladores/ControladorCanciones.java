package com.ContrapisoWeb.LogicaAplicacion.Controladores;

import com.ContrapisoWeb.LogicaConexion.Interfaces.RepositorioCancion;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Artista;
import com.ContrapisoWeb.LogicaNegocio.Dominio.Cancion;
import com.ContrapisoWeb.LogicaNegocio.Fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ControladorCanciones {
    @Autowired
    Fachada fachada;
    @Autowired
    RepositorioCancion repositorioCancion;

    @PostMapping("/nuevaCancion")
    public ResponseEntity<?> nuevaCancion(@RequestParam(value = "nombre") String nombre,
                                          @RequestParam(value = "idArtista") int idArtista,
                                          @RequestParam(value = "url") String url) {
        try {
            if (nombre.isEmpty() || nombre.isBlank()) {
                return ResponseEntity.ofNullable(nombre);
            }
            Optional<Artista> artistaOptional = fachada.getArtistaPorId(idArtista);
            if (!artistaOptional.isPresent()) {
                return ResponseEntity.status(404).body("Artista no encontrado");
            }
            if (url.isEmpty() || url.isBlank()) {
                return ResponseEntity.ofNullable(url);
            }
            Artista artista = artistaOptional.get();
            Cancion cancion = new Cancion(nombre, url);
            cancion.agregarArtista(artista);
            repositorioCancion.save(cancion);

            //ToDo: verificar funcionamiento y optimizar codigo;

            return ResponseEntity.ok("Se ha creado la cancion");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
