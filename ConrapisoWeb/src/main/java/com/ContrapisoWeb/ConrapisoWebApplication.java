package com.ContrapisoWeb;

import LogicaNegocio.Fachada.Fachada;
import LogicaNegocio.Servicios.ServicioArtistas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ContrapisoWeb", "LogicaNegocio.Fachada", "LogicaAplicacion.Controladores"})
public class ConrapisoWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConrapisoWebApplication.class, args);
	}

}
