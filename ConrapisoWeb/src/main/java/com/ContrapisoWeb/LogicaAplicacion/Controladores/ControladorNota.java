package com.ContrapisoWeb.LogicaAplicacion.Controladores;


import com.ContrapisoWeb.LogicaNegocio.Dominio.Nota;
import com.ContrapisoWeb.LogicaNegocio.Servicios.ServicioNotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas")
public class ControladorNota {
    @Autowired
    private ServicioNotas servicio;



    @PostMapping("/publicar")
    public ResponseEntity<Nota> publicarNota(@RequestParam int id) {
        try {
            Nota notaPublicada = servicio.publicarNota(id);
            return ResponseEntity.ok(notaPublicada);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null); // Maneja errores de manera adecuada
        }
    }

}
